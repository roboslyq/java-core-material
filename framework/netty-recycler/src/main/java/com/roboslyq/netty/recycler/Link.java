/**
 * Copyright (C), 2015-2020
 * FileName: Link
 * Author:   luo.yongqian
 * Date:     2020/7/24 9:05
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/7/24 9:05      1.0.0               创建
 */
package com.roboslyq.netty.recycler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 // Let Link extend AtomicInteger for intrinsics. The Link itself will be used as writerIndex.
 // 收回对象存储在链表某个Link节点里，当Link节点存储的收回对象满了时会新建1个Link放在Link链表尾。
 * @author roboslyq
 * @date 2020/7/24
 * @since 1.0.0
 */
public class Link extends AtomicInteger {
    public final DefaultHandle<?>[] elements = new DefaultHandle[Recycler.LINK_CAPACITY];
    public int readIndex;
    Link next;
}