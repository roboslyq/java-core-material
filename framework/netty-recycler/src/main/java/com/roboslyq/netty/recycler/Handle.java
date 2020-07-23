/**
 * Copyright (C), 2015-2020
 * FileName: Handle
 * Author:   luo.yongqian
 * Date:     2020/7/22 9:11
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/7/22 9:11      1.0.0               创建
 */
package com.roboslyq.netty.recycler;

/**
 *
 * 提供对象的回收功能，由子类进行复写
 * 目前该接口只有两个实现：NOOP_HANDLE和DefaultHandle
 * @author roboslyq
 * @date 2020/7/22
 * @since 1.0.0
 */

public interface Handle<T> {
    void recycle(T object);
}