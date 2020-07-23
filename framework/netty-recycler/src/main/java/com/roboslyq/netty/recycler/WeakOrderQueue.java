/**
 * Copyright (C), 2015-2020
 * FileName: WeakOrderQueue
 * Author:   luo.yongqian
 * Date:     2020/7/22 9:09
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/7/22 9:09      1.0.0               创建
 */
package com.roboslyq.netty.recycler;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.min;

/**
 *
 * 〈  **
 *      * 多线程共享的队列:
 *      * 存储其它线程收回到分配线程的对象，当某个线程从Stack中获取不到对象时会从WeakOrderQueue中获取对象。
 *      〉
 * @author roboslyq
 * @date 2020/7/22
 * @since 1.0.0
 */
public class WeakOrderQueue {
    /**
     * 如果DELAYED_RECYCLED中的key-value对已经达到了maxDelayedQueues，
     * 对于后续的Stack，其对应的WeakOrderQueue设置为DUMMY，
     * 后续如果检测到DELAYED_RECYCLED中对应的Stack的value是WeakOrderQueue.DUMMY时，直接返回，不做存储操作
     */
    static final WeakOrderQueue DUMMY = new WeakOrderQueue();

    // Let Link extend AtomicInteger for intrinsics. The Link itself will be used as writerIndex.
    // 收回对象存储在链表某个Link节点里，当Link节点存储的收回对象满了时会新建1个Link放在Link链表尾。
    @SuppressWarnings("serial")
    static final class Link extends AtomicInteger {
        private final DefaultHandle<?>[] elements = new DefaultHandle[Recycler.LINK_CAPACITY];

        private int readIndex;
        Link next;
    }

    // This act as a place holder for the head Link but also will reclaim space once finalized.
    // Its important this does not hold any reference to either Stack or WeakOrderQueue.
    static final class Head {
        private final AtomicInteger availableSharedCapacity;

        Link link;

        Head(AtomicInteger availableSharedCapacity) {
            this.availableSharedCapacity = availableSharedCapacity;
        }

        /// TODO: In the future when we move to Java9+ we should use java.lang.ref.Cleaner.
        @Override
        protected void finalize() throws Throwable {
            try {
                super.finalize();
            } finally {
                Link head = link;
                link = null;
                while (head != null) {
                    reclaimSpace(Recycler.LINK_CAPACITY);
                    Link next = head.next;
                    // Unlink to help GC and guard against GC nepotism.
                    head.next = null;
                    head = next;
                }
            }
        }

        void reclaimSpace(int space) {
            assert space >= 0;
            availableSharedCapacity.addAndGet(space);
        }

        boolean reserveSpace(int space) {
            return reserveSpace(availableSharedCapacity, space);
        }

        static boolean reserveSpace(AtomicInteger availableSharedCapacity, int space) {
            assert space >= 0;
            for (;;) {
                int available = availableSharedCapacity.get();
                if (available < space) {
                    return false;
                }
                if (availableSharedCapacity.compareAndSet(available, available - space)) {
                    return true;
                }
            }
        }
    }
    //Link数组
    // chain of data items
    private final Head head;
    private Link tail;
    // pointer to another queue of delayed items for the same stack
    //向同一堆栈的另一个延迟项队列的指针
    public WeakOrderQueue next;
    /**
     * 当前WeakReference对应的拥有者(Thread)
     * 1、why WeakReference？与Stack相同。
     * 2、作用是在poll的时候，如果owner不存在了，则需要将该线程所包含的WeakOrderQueue的元素释放，然后从链表中删除该Queue。
     */
    public final WeakReference<Thread> owner;
    //WeakOrderQueue的唯一标记
    private final int id = Recycler.ID_GENERATOR.getAndIncrement();

    private WeakOrderQueue() {
        owner = null;
        head = new Head(null);
    }

    /**
     * 构造函数
     * @param stack
     * @param thread
     */
    private WeakOrderQueue(Stack<?> stack, Thread thread) {
        //创建tail
        tail = new Link();

        // Its important that we not store the Stack itself in the WeakOrderQueue as the Stack also is used in
        // the WeakHashMap as key. So just store the enclosed AtomicInteger which should allow to have the
        // Stack itself GCed.
        //创建head<注意,这里我们没有保存stack对象本身,而是仅仅保存>
        head = new Head(stack.availableSharedCapacity);

        head.link = tail;
        owner = new WeakReference<Thread>(thread);
    }
    /**
     * 正常情况，不同线程收回资源，会以此方法为入口。
     * 此方法注意构造函数的两个关键参数:
     * @param stack     对应Recycler中的中stack(此stack与一个具体的Thread A 绑定，即创建Recycler所在的线程)
     * @param thread    当前线程Thread B，即Thread A将生产出来的对象实例(被包装为一个具体的Handle)给当前线程Thread B使用。然后B发起recycle()操作。
     * 所以WeakOrderQueue是由两个元素确定唯一的：另一个线程生产的stack 和 当前收回线程 thread B。所以如果生产线程Thread A生产多个Handle分别给Thread B, Thread C等
     *                  多个线程使用，那么，这个WeakOrderQueue会有多个，并且最终组成一个link链表结构。
     */
    static WeakOrderQueue newQueue(Stack<?> stack, Thread thread) {
        //构造函数
        final WeakOrderQueue queue = new WeakOrderQueue(stack, thread);
        // Done outside of the constructor to ensure WeakOrderQueue.this does not escape the constructor and so
        // may be accessed while its still constructed.
        stack.setHead(queue);

        return queue;
    }

    public void setNext(WeakOrderQueue next) {
        assert next != this;
        this.next = next;
    }

    /**
     * Allocate a new {@link WeakOrderQueue} or return {@code null} if not possible.
     * 分配一个新的WeakOrderQueue
     */
    static WeakOrderQueue allocate(Stack<?> stack, Thread thread) {
        // We allocated a Link so reserve the space
        // 如果该stack的可用共享空间还能再容下1个WeakOrderQueue，那么创建1个WeakOrderQueue，否则返回null
        return Head.reserveSpace(stack.availableSharedCapacity, Recycler.LINK_CAPACITY)
                ? newQueue(stack, thread) : null;
    }

    void add(DefaultHandle<?> handle) {
        handle.lastRecycledId = id;

        Link tail = this.tail;
        int writeIndex;
        if ((writeIndex = tail.get()) == Recycler.LINK_CAPACITY) {
            if (!head.reserveSpace(Recycler.LINK_CAPACITY)) {
                // Drop it.
                return;
            }
            // We allocate a Link so reserve the space
            this.tail = tail = tail.next = new Link();

            writeIndex = tail.get();
        }
        tail.elements[writeIndex] = handle;
        handle.stack = null;
        // we lazy set to ensure that setting stack to null appears before we unnull it in the owning thread;
        // this also means we guarantee visibility of an element in the queue if we see the index updated
        tail.lazySet(writeIndex + 1);
    }

    boolean hasFinalData() {
        return tail.readIndex != tail.get();
    }

    // transfer as many items as we can from this queue to the stack, returning true if any were transferred
    @SuppressWarnings("rawtypes")
    boolean transfer(Stack<?> dst) {
        Link head = this.head.link;
        if (head == null) {
            return false;
        }

        if (head.readIndex == Recycler.LINK_CAPACITY) {
            if (head.next == null) {
                return false;
            }
            this.head.link = head = head.next;
        }

        final int srcStart = head.readIndex;
        int srcEnd = head.get();
        final int srcSize = srcEnd - srcStart;
        if (srcSize == 0) {
            return false;
        }

        final int dstSize = dst.size;
        final int expectedCapacity = dstSize + srcSize;

        if (expectedCapacity > dst.elements.length) {
            final int actualCapacity = dst.increaseCapacity(expectedCapacity);
            srcEnd = min(srcStart + actualCapacity - dstSize, srcEnd);
        }

        if (srcStart != srcEnd) {
            final DefaultHandle[] srcElems = head.elements;
            final DefaultHandle[] dstElems = dst.elements;
            int newDstSize = dstSize;
            for (int i = srcStart; i < srcEnd; i++) {
                DefaultHandle element = srcElems[i];
                if (element.recycleId == 0) {
                    element.recycleId = element.lastRecycledId;
                } else if (element.recycleId != element.lastRecycledId) {
                    throw new IllegalStateException("recycled already");
                }
                srcElems[i] = null;

                if (dst.dropHandle(element)) {
                    // Drop the object.
                    continue;
                }
                element.stack = dst;
                dstElems[newDstSize ++] = element;
            }

            if (srcEnd == Recycler.LINK_CAPACITY && head.next != null) {
                // Add capacity back as the Link is GCed.
                this.head.reclaimSpace(Recycler.LINK_CAPACITY);
                this.head.link = head.next;
            }

            head.readIndex = srcEnd;
            if (dst.size == newDstSize) {
                return false;
            }
            dst.size = newDstSize;
            return true;
        } else {
            // The destination stack is full already.
            return false;
        }
    }
}