/**
 * Copyright (C), 2015-2020
 * FileName: Head
 * Author:   luo.yongqian
 * Date:     2020/7/24 9:08
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/7/24 9:08      1.0.0               创建
 */
package com.roboslyq.netty.recycler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/7/24
 * @since 1.0.0
 */
// This act as a place holder for the head Link but also will reclaim space once finalized.
// Its important this does not hold any reference to either Stack or WeakOrderQueue.
public class Head {
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