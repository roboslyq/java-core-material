/**
 * Copyright (C), 2015-2020
 * FileName: DefaultHandle
 * Author:   luo.yongqian
 * Date:     2020/7/22 9:12
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/7/22 9:12      1.0.0               创建
 */
package com.roboslyq.netty.recycler;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/7/22
 * @since 1.0.0
 */

/**
 * 1、原始对象T的包装类,使用value<Object>属性持有原始对象的引用
 * 2、使用栈Stack结构
 * @param <T>
 */
public class DefaultHandle<T> implements Handle<T> {
    /**
     * 1、在pushNow() 方法中，与 recycleId 一样，被赋值为：OWN_THREAD_ID
     * 2、在pushLater中的add(DefaultHandle handle)操作中 ，被赋值为id（当前的WeakOrderQueue的唯一ID）
     * 3、在poll()中被重置为0
     */
    public int lastRecycledId;
    /**
     * 1、在pushNow() 方法中，与 lastRecycledId 一样，被赋值为：OWN_THREAD_ID
     * 2、在poll()中重置为0
     */
    public int recycleId;
    /**
     * 当前对象是否已经被回收(因为当前DefaultHandler可能已经分配给其它线程使用了，所以需要回收)
     */
    boolean hasBeenRecycled;
    /**
     * 当前的DefaultHandle对象所属的Stack
     */
    public Stack<?> stack;
    /**
     * 原始被池化的对象
     */
    public Object value;

    DefaultHandle(Stack<?> stack) {
        this.stack = stack;
    }

    /**
     * 收回对象
     * @param object
     */
    @Override
    public void recycle(Object object) {
        if (object != value) {
            throw new IllegalArgumentException("object does not belong to handle");
        }

        Stack<?> stack = this.stack;
        if (lastRecycledId != recycleId || stack == null) {
            throw new IllegalStateException("recycled already");
        }
        //收回对象，this指的是当前的DefaultHandle对象:核心方法Stack#push()
        stack.push(this);
    }
}