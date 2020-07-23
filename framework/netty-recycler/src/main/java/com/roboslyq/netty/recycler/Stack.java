/**
 * Copyright (C), 2015-2020
 * FileName: Stack
 * Author:   luo.yongqian
 * Date:     2020/7/22 9:10
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/7/22 9:10      1.0.0               创建
 */
package com.roboslyq.netty.recycler;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Netty为了池化自己实现的Stack数据结构: 对象池的真正的 “池”
 * @author roboslyq
 * @date 2020/7/22
 * @since 1.0.0
 */
public class Stack<T> {

    // we keep a queue of per-thread queues, which is appended to once only, each time a new thread other
    // than the stack owner recycles: when we run out of items in our stack we iterate this collection
    // to scavenge those that can be reused. this permits us to incur minimal thread synchronisation whilst
    // still recycling all items.
    final Recycler<T> parent;

    // We store the Thread in a WeakReference as otherwise we may be the only ones that still hold a strong
    // Reference to the Thread itself after it died because DefaultHandle will hold a reference to the Stack.
    //
    // The biggest issue is if we do not use a WeakReference the Thread may not be able to be collected at all if
    // the user will store a reference to the DefaultHandle somewhere and never clear this reference (or not clear
    // it in a timely manner).
    /**
     * 该Stack所属的线程
     * why WeakReference?
     * 假设该线程对象在外界已经没有强引用了，那么实际上该线程对象就可以被回收了。但是如果此处用的是强引用，那么虽然外界不再对该线程有强引用，
     * 但是该stack对象还持有强引用（假设用户存储了DefaultHandle对象，然后一直不释放，而DefaultHandle对象又持有stack引用），导致该线程对象无法释放。
     *
     */
    final WeakReference<Thread> threadRef;

    /**
     * 可用的共享内存大小，默认为maxCapacity/maxSharedCapacityFactor = 4k/2 = 2k = 2048
     * 假设当前的Stack是线程A的，则其他线程B~X等去回收线程A创建的对象时，可回收最多A创建的多少个对象
     * 注意：那么实际上线程A创建的对象最终最多可以被回收maxCapacity + availableSharedCapacity个，默认为6k个
     * why AtomicInteger?
     * 当线程B和线程C同时创建线程A的WeakOrderQueue的时候，会同时分配内存，需要同时操作availableSharedCapacity
     * 具体见：WeakOrderQueue.allocate
     */
    final AtomicInteger availableSharedCapacity;
    //DELAYED_RECYCLED中最多可存储的{Stack，WeakOrderQueue}键值对个数
    final int maxDelayedQueues;
    //elements最大的容量：默认最大为4k，4096
    private final int maxCapacity;
    //默认为8-1=7，即2^3-1，控制每8个元素只有一个可以被recycle，其余7个被扔掉
    private final int ratioMask;
    //Stack底层数据结构，真正的用来存储数据
    public DefaultHandle<?>[] elements;
    //elements中的元素个数，同时也可作为操作数组的下标
    public int size;
    /**
     * 每有一个元素将要被回收, 则该值+1，例如第一个被回收的元素的handleRecycleCount=handleRecycleCount+1=0
     * 与ratioMask配合，用来决定当前的元素是被回收还是被drop。
     * 例如 ++handleRecycleCount & ratioMask（7），其实相当于 ++handleRecycleCount % 8，
     * 则当 ++handleRecycleCount = 0/8/16/...时，元素被回收，其余的元素直接被drop
     */
    private int handleRecycleCount = -1; // Start with -1 so the first one will be recycled.
    private WeakOrderQueue cursor, prev;

    /**
     * 该值是当线程B回收线程A创建的对象时，线程B会为线程A的Stack对象创建一个WeakOrderQueue对象，
     * 该WeakOrderQueue指向这里的head，用于后续线程A对对象的查找操作
     * Q: why volatile?
     * A: 假设线程A正要读取对象X，此时需要从其他线程的WeakOrderQueue中读取，假设此时线程B正好创建Queue，并向Queue中放入一个对象X；假设恰好次Queue就是线程A的Stack的head
     * 使用volatile可以立即读取到该queue。
     *
     * 对于head的设置，具有同步问题。具体见此处的volatile和synchronized void setHead(WeakOrderQueue queue)
     */
    private volatile WeakOrderQueue head;

    Stack(Recycler<T> parent, Thread thread, int maxCapacity, int maxSharedCapacityFactor,
          int ratioMask, int maxDelayedQueues) {
        this.parent = parent;
        threadRef = new WeakReference<Thread>(thread);
        this.maxCapacity = maxCapacity;
        availableSharedCapacity = new AtomicInteger(max(maxCapacity / maxSharedCapacityFactor, Recycler.LINK_CAPACITY));
        elements = new DefaultHandle[min(Recycler.INITIAL_CAPACITY, maxCapacity)];
        this.ratioMask = ratioMask;
        this.maxDelayedQueues = maxDelayedQueues;
    }

    // Marked as synchronized to ensure this is serialized.
    /**
     * 假设线程B和线程C同时回收线程A的对象时，有可能会同时newQueue，就可能同时setHead，所以这里需要加锁
     * 以head==null的时候为例，
     * 加锁：
     * 线程B先执行，则head = 线程B的queue；之后线程C执行，此时将当前的head也就是线程B的queue作为线程C的queue的next，组成链表，之后设置head为线程C的queue
     * 不加锁：
     * 线程B先执行queue.setNext(head);此时线程B的queue.next=null->线程C执行queue.setNext(head);线程C的queue.next=null
     * -> 线程B执行head = queue;设置head为线程B的queue -> 线程C执行head = queue;设置head为线程C的queue
     *
     * 注意：此时线程B和线程C的queue没有连起来，则之后的poll()就不会从B进行查询。（B就是资源泄露）
     */
    synchronized void setHead(WeakOrderQueue queue) {
        queue.setNext(head);
        head = queue;
    }

    int increaseCapacity(int expectedCapacity) {
        int newCapacity = elements.length;
        int maxCapacity = this.maxCapacity;
        do {
            newCapacity <<= 1;
        } while (newCapacity < expectedCapacity && newCapacity < maxCapacity);

        newCapacity = min(newCapacity, maxCapacity);
        if (newCapacity != elements.length) {
            elements = Arrays.copyOf(elements, newCapacity);
        }

        return newCapacity;
    }

    /**
     * Stack获取元素
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    DefaultHandle<T> pop() {
        // 当前栈元素保存的数量
        int size = this.size;
        // 如果当前栈元素数据为0，则尝试从WeakOrderQueue获取(即其它线程归还的对象)
        if (size == 0) {
            // 如果当前Stack没有多余的元素，则从WeakOrderQueue中获取
            if (!scavenge()) {
                return null;
            }
            size = this.size;
        }
        size --;
        DefaultHandle ret = elements[size];
        elements[size] = null;
        if (ret.lastRecycledId != ret.recycleId) {
            throw new IllegalStateException("recycled multiple times");
        }
        /*
         * 清空当前线程ID，因为recycleId 或 lastRecycledId保存的是当前线程的ID,如果ID不为空，
         * 表示Handle已经被回收，不能再发起回收操作。
         * 所以，在使用对象前，需要将这个“回收”标识清零，以便后续能正常归还。
         * 详情见push()方法
         */
        ret.recycleId = 0;
        ret.lastRecycledId = 0;
        this.size = size;
        return ret;
    }

    /**
     * 遍列WeakOrderQueue(异线程归还的对象)
     * @return
     */
    boolean scavenge() {
        // continue an existing scavenge, if any
        if (scavengeSome()) {
            return true;
        }

        // reset our scavenge cursor
        prev = null;
        cursor = head;
        return false;
    }

    boolean scavengeSome() {
        WeakOrderQueue prev;
        WeakOrderQueue cursor = this.cursor;
        if (cursor == null) {
            prev = null;
            cursor = head;
            if (cursor == null) {
                return false;
            }
        } else {
            prev = this.prev;
        }

        boolean success = false;
        do {
            if (cursor.transfer(this)) {
                success = true;
                break;
            }
            WeakOrderQueue next = cursor.next;
            if (cursor.owner.get() == null) {
                // If the thread associated with the queue is gone, unlink it, after
                // performing a volatile read to confirm there is no data left to collect.
                // We never unlink the first queue, as we don't want to synchronize on updating the head.
                if (cursor.hasFinalData()) {
                    for (;;) {
                        if (cursor.transfer(this)) {
                            success = true;
                        } else {
                            break;
                        }
                    }
                }

                if (prev != null) {
                    prev.setNext(next);
                }
            } else {
                prev = cursor;
            }

            cursor = next;

        } while (cursor != null && !success);

        this.prev = prev;
        this.cursor = cursor;
        return success;
    }

    /**
     * 收回对象到Stack中
     * @param item
     */
    void push(DefaultHandle<?> item) {
        //获取当前线程
        Thread currentThread = Thread.currentThread();
        //如果当前线程是Stack对应的线程threadRef,则直接pushnow()
        if (threadRef.get() == currentThread) {
            // The current Thread is the thread that belongs to the Stack, we can try to push the object now.
            //如果该stack就是本线程的stack，那么直接把DefaultHandle放到该stack的数组里
            pushNow(item);
        } else {
            // The current Thread is not the one that belongs to the Stack
            // (or the Thread that belonged to the Stack was collected already), we need to signal that the push
            // happens later.
            //如果该stack不是本线程的stack，那么把该DefaultHandle放到该stack的WeakOrderQueue中
            pushLater(item, currentThread);
        }
    }

    /**
     * 直接把DefaultHandle放到stack的数组里，如果数组满了那么扩展该数组为当前2倍大小
     * @param item
     */
    private void pushNow(DefaultHandle<?> item) {
        //如果对象已经回收，不需要继续回收，抛出异常
        if ((item.recycleId | item.lastRecycledId) != 0) {
            throw new IllegalStateException("recycled already");
        }
        //记录 recycleId
        item.recycleId = item.lastRecycledId = Recycler.OWN_THREAD_ID;

        int size = this.size;
        if (size >= maxCapacity || dropHandle(item)) {
            // Hit the maximum capacity or should drop - drop the possibly youngest object.
            return;
        }
        //扩容(2的倍数)
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, min(size << 1, maxCapacity));
        }
        //存值
        elements[size] = item;
        this.size = size + 1;
    }

    /**
     * 非当前线程回收:先将item元素加入WeakOrderQueue，后续再从WeakOrderQueue中将元素压入Stack中
     * @param item
     * @param thread
     */
    private void pushLater(DefaultHandle<?> item, Thread thread) {
        // we don't want to have a ref to the queue as the value in our weak map
        // so we null it out; to ensure there are no races with restoring it later
        // we impose a memory ordering here (no-op on x86)
        /*
         * Recycler有1个stack->WeakOrderQueue映射，每个stack会映射到1个WeakOrderQueue，这个WeakOrderQueue是该stack关联的其它线程WeakOrderQueue链表的head WeakOrderQueue。
         * 当其它线程回收对象到该stack时会创建1个WeakOrderQueue中并加到stack的WeakOrderQueue链表中。
         */
        Map<Stack<?>, WeakOrderQueue> delayedRecycled = Recycler.DELAYED_RECYCLED.get();
        // this = Stack，如果当前Stack对应的WeakOrderQueue为空，则进行初始化
        WeakOrderQueue queue = delayedRecycled.get(this);
        //如果queue为空，进行初始化
        if (queue == null) {
            // 如果DELAYED_RECYCLED中的key-value对已经达到了maxDelayedQueues，则后续的无法回收 - 内存保护
            if (delayedRecycled.size() >= maxDelayedQueues) {
                // Add a dummy queue so we know we should drop the object
                // 如果delayedRecycled满了那么将1个伪造的WeakOrderQueue（DUMMY）放到delayedRecycled中，并丢弃该对象（DefaultHandle）
                delayedRecycled.put(this, WeakOrderQueue.DUMMY);
                return;
            }
            // Check if we already reached the maximum number of delayed queues and if we can allocate at all.
            if ((queue = WeakOrderQueue.allocate(this, thread)) == null) {
                // drop object
                return;
            }
            // 创建1个WeakOrderQueue
            delayedRecycled.put(this, queue);
        } else if (queue == WeakOrderQueue.DUMMY) {
            // drop object
            return;
        }
        //将对象放入到该stack对应的WeakOrderQueue中
        queue.add(item);
    }
    /**
     * 两个drop的时机
     * 1、pushNow：当前线程将数据push到Stack中
     * 2、transfer：将其他线程的WeakOrderQueue中的数据转移到当前的Stack中
     */
    boolean dropHandle(DefaultHandle<?> handle) {
        if (!handle.hasBeenRecycled) {
            if ((++handleRecycleCount & ratioMask) != 0) {
                // Drop the object.
                return true;
            }
            handle.hasBeenRecycled = true;
        }
        return false;
    }

    DefaultHandle<T> newHandle() {
        return new DefaultHandle<T>(this);
    }
}