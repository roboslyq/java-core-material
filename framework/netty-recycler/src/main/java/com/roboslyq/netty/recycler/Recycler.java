/*
 * Copyright 2013 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.roboslyq.netty.recycler;


import com.roboslyq.netty.recycler.util.FastThreadLocal;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Light-weight object pool based on a thread-local stacDefaultHandlek.
 * 1、轻量级的对象池化技术，基于Thread-Local技术栈。
 * 2、Recycler用来实现对象池，其中对应堆内存和直接内存的池化实现分别是PooledHeapByteBuf和PooledDirectByteBuf。
 *
 *    Recycler主要提供了3个方法：
         get():获取一个对象。
         recycle(T, Handle):收回一个对象，T为对象泛型。
         newObject(Handle):当没有可用对象时创建对象的实现方法。

 * 3、对象回收池
 *      1)、因为具有抽象方法，所以Recycler是抽象类
 *      2)、几乎不需要锁
 *      3)、线程A创建的对象X只能由线程A进行get，线程B无法get，但是假设线程A将对象X传递给线程B进行使用，线程B使用结束后，可以进行recycle到WeakOrderQueue中；
 *          之后线程A进行get的时候会将对象X转移到自己的Stack中
 *
 * 4、同步问题：
 *      （1）假设线程A进行get，线程B也进行get，无锁（二者各自从自己的stack或者从各自的weakOrderQueue中进行获取）
 *      （2）假设线程A进行get对象X，线程B进行recycle对象X，无锁（假设A无法直接从其Stack获取，从WeakOrderQueue进行获取，由于stack.head是volatile的，线程Brecycle的对象X可以被线程A立即获取）
 *      （3）假设线程C和线程Brecycle线程A的对象X，此时需要加锁（具体见Stack.setHead()）
 *
 * 5、使用方式
 *
 *    private static Recycler<HandledObject> newRecycler(int max) {
 *         return new Recycler<HandledObject>(max) {
 *             //如果当前缓冲池为空，训实例化一个新对象
 *             @Override
 *             protected HandledObject newObject(
 *                     Recycler.Handle<HandledObject> handle) {
 *                 return new HandledObject(handle);
 *             }
 *         };
 *     }
 * @param <T> the type of the pooled object
 *           泛型<T>是具体的缓存对象。
 */
public abstract class Recycler<T> {

    /**
     * 表示一个不需要回收的包装对象，用于在禁止使用Recycler功能时进行占位的功能
     * 仅当io.netty.recycler.maxCapacityPerThread<=0时用到
     */
    @SuppressWarnings("rawtypes")
    private static final Handle NOOP_HANDLE = new Handle() {
        @Override
        public void recycle(Object object) {
            // NOOP
        }
    };
    /**
     * 唯一ID生成器,用在两处：
     *      1、当前线程ID
     *      2、WeakOrderQueue的id
     */
    public static final AtomicInteger ID_GENERATOR = new AtomicInteger(Integer.MIN_VALUE);
    /**
     * static变量, 生成并获取一个唯一id.
     * 用于pushNow()中的item.recycleId和item.lastRecycleId的设定
     */
    public static final int OWN_THREAD_ID = ID_GENERATOR.getAndIncrement();

    private static final int DEFAULT_INITIAL_MAX_CAPACITY_PER_THREAD = 4 * 1024; // Use 4k instances as default.
    /**
     * 每个Stack默认的最大容量
     * 注意：
     * 1、当io.netty.recycler.maxCapacityPerThread<=0时，禁用回收功能（在netty中，只有=0可以禁用，<0默认使用4k）
     * 2、Recycler中有且只有两个地方存储DefaultHandle对象（Stack和Link），
     * 最多可存储MAX_CAPACITY_PER_THREAD + 最大可共享容量 = 4k + 4k/2 = 6k
     *
     * 实际上，在netty中，Recycler提供了两种设置属性的方式
     *      第一种：-Dio.netty.recycler.ratio等jvm启动参数方式
     *      第二种：Recycler(int maxCapacityPerThread)构造器传入方式
     */
    private static final int DEFAULT_MAX_CAPACITY_PER_THREAD;
    /**
     * 每个Stack默认的初始容量，默认为256
     * 后续根据需要进行扩容，直到<=MAX_CAPACITY_PER_THREAD
     */
    public static final int INITIAL_CAPACITY;
    /**
     * 最大可共享的容量因子。
     * 最大可共享的容量 = maxCapacity / maxSharedCapacityFactor，maxSharedCapacityFactor默认为2
     */
    private static final int MAX_SHARED_CAPACITY_FACTOR;
    /**
     * 每个线程可拥有多少个WeakOrderQueue，默认为2*cpu核数
     * 实际上就是当前线程的Map<Stack<?>, WeakOrderQueue>的size最大值
     */
    private static final int MAX_DELAYED_QUEUES_PER_THREAD;
    /**
     * WeakOrderQueue中的Link中的数组DefaultHandle<?>[] elements容量，默认为16，
     * 当一个Link中的DefaultHandle元素达到16个时，会新创建一个Link进行存储，这些Link组成链表，当然
     * 所有的Link加起来的容量要<=最大可共享容量。
     */
    public static final int LINK_CAPACITY;
    /**
     * 回收因子，默认为8。
     * 即默认每8个对象，允许回收一次，直接扔掉7个，可以让recycler的容量缓慢的增大，避免爆发式的请求
     */
    private static final int RATIO;

    static {
        // In the future, we might have different maxCapacity for different object types.
        // e.g. io.netty.recycler.maxCapacity.writeTask
        //      io.netty.recycler.maxCapacity.outboundBuffer
        int maxCapacityPerThread =  DEFAULT_INITIAL_MAX_CAPACITY_PER_THREAD;
        if (maxCapacityPerThread < 0) {
            maxCapacityPerThread = DEFAULT_INITIAL_MAX_CAPACITY_PER_THREAD;
        }

        DEFAULT_MAX_CAPACITY_PER_THREAD = maxCapacityPerThread;

        MAX_SHARED_CAPACITY_FACTOR =  2;

        MAX_DELAYED_QUEUES_PER_THREAD = Runtime.getRuntime().availableProcessors() * 2;

        LINK_CAPACITY =  16;

        // By default we allow one push to a Recycler for each 8th try on handles that were never recycled before.
        // This should help to slowly increase the capacity of the recycler while not be too sensitive to allocation
        // bursts.
        RATIO =  8;

        INITIAL_CAPACITY = min(DEFAULT_MAX_CAPACITY_PER_THREAD, 256);
    }

    private final int maxCapacityPerThread;
    private final int maxSharedCapacityFactor;
    private final int ratioMask;
    private final int maxDelayedQueuesPerThread;
    /**
     * 完成Stack初始化。
     *
     *  1、每个Recycler类（而不是每一个Recycler对象）都有一个DELAYED_RECYCLED
     *   原因：可以根据一个Stack<T>对象唯一的找到一个WeakOrderQueue对象，所以此处不需要每个对象建立一个DELAYED_RECYCLED
     *  2、由于DELAYED_RECYCLED是一个类变量，所以需要包容多个T，此处泛型需要使用?
     *  3、WeakHashMap：当Stack没有强引用可达时，整个Entry{Stack<?>, WeakOrderQueue}都会加入相应的弱引用队列等待回收
     *
     */
    private final FastThreadLocal<Stack<T>> threadLocal = new FastThreadLocal<Stack<T>>() {
        @Override
        protected Stack<T> initialValue() {
            /*
             * 完成Stack初始化
             */
            return new Stack<T>(Recycler.this, Thread.currentThread(), maxCapacityPerThread, maxSharedCapacityFactor,
                    ratioMask, maxDelayedQueuesPerThread);
        }
        @Override
        protected void onRemoval(Stack<T> value) {
            // Let us remove the WeakOrderQueue from the WeakHashMap directly if its safe to remove some overhead
            if (value.threadRef.get() == Thread.currentThread()) {
               if (DELAYED_RECYCLED.isSet()) {
                   DELAYED_RECYCLED.get().remove(value);
               }
            }
        }
    };

    protected Recycler() {
        this(DEFAULT_MAX_CAPACITY_PER_THREAD);
    }

    protected Recycler(int maxCapacityPerThread) {
        this(maxCapacityPerThread, MAX_SHARED_CAPACITY_FACTOR);
    }

    protected Recycler(int maxCapacityPerThread, int maxSharedCapacityFactor) {
        this(maxCapacityPerThread, maxSharedCapacityFactor, RATIO, MAX_DELAYED_QUEUES_PER_THREAD);
    }

    protected Recycler(int maxCapacityPerThread, int maxSharedCapacityFactor,
                       int ratio, int maxDelayedQueuesPerThread) {
        ratioMask =  ratio - 1;
        if (maxCapacityPerThread <= 0) {
            this.maxCapacityPerThread = 0;
            this.maxSharedCapacityFactor = 1;
            this.maxDelayedQueuesPerThread = 0;
        } else {
            this.maxCapacityPerThread = maxCapacityPerThread;
            this.maxSharedCapacityFactor = max(1, maxSharedCapacityFactor);
            this.maxDelayedQueuesPerThread = max(0, maxDelayedQueuesPerThread);
        }
    }

    /**
     * 获取对象
     * @return
     */
    @SuppressWarnings("unchecked")
    public final T get() {
        /*
         * 0、如果maxCapacityPerThread == 0，禁止回收功能
         * 创建一个对象，其Recycler.Handle<User> handle属性为NOOP_HANDLE，该对象的recycle(Object object)不做任何事情，即不做回收
         */
        if (maxCapacityPerThread == 0) {
            return newObject((Handle<T>) NOOP_HANDLE);
        }
        //1、获取当前线程的Stack<T>对象
        Stack<T> stack = threadLocal.get();
        //2、=====> 从Stack<T>对象中获取DefaultHandle<T>：核心方法Stack#pop()
        DefaultHandle<T> handle = stack.pop();

        if (handle == null) {//首次进入为true,需要创建对象
            //3、 新建一个DefaultHandle对象 -> 然后新建T对象 -> 存储到DefaultHandle对象
            //   此处会发现一个DefaultHandle对象对应一个Object对象，二者相互包含。
            handle = stack.newHandle();
            handle.value = newObject(handle);
        }
        return (T) handle.value;
    }

    /**
     * @deprecated use {@link Handle#recycle(Object)}.
     * 收回对象(将发出去给线程的对象收回回来，可以供其它线程重新使用)
     * 此方法已经过时，请直接使用 Handle#recycle(Object)方法：此方法能完成不同线程间的对象回收。
     */
    @Deprecated
    public final boolean recycle(T o, Handle<T> handle) {
        if (handle == NOOP_HANDLE) {
            return false;
        }

        DefaultHandle<T> h = (DefaultHandle<T>) handle;
        if (h.stack.parent != this) {
            return false;
        }
        //收回对象o
        h.recycle(o);
        return true;
    }

    final int threadLocalCapacity() {
        return threadLocal.get().elements.length;
    }

    final int threadLocalSize() {
        return threadLocal.get().size;
    }

    /**
     * 抽象方法，由具体的业务子类实现
     * @param handle
     * @return
     */
    protected abstract T newObject(Handle<T> handle);




    /**
     * 类属性，所有实例共享，从而实现不同线程间的对象回收。比如，A线程创建对象 POJO1,传递给B线程使用，B线程也可以对对象POJO1进行回收。
     * Recycler <--1:1-->Thread
     * Recycler <--1:1-->Stack
     * Recycler <--1:N-->WeakOrderQueue (通过Map<Stack<?>, WeakOrderQueue>)实现。即一个JVM，Recycler对应N个WeakOrderQueue，并且这个WeakOrderQueue是共享的区域。
     *      但是，每个不同的线程访问WeakOrderQueue时，可以通过自己持有的Stack这个key找到具体的WeakOrderQueue.
     */
    public static final FastThreadLocal<Map<Stack<?>, WeakOrderQueue>> DELAYED_RECYCLED =
            new FastThreadLocal<Map<Stack<?>, WeakOrderQueue>>() {
                @Override
                protected Map<Stack<?>, WeakOrderQueue> initialValue() {
                    return new WeakHashMap<Stack<?>, WeakOrderQueue>();
                }
            };
}