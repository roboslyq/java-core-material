/**
 * Copyright (C), 2015-2019
 * FileName: HasUpstreamPublisher
 * Author:   luo.yongqian
 * Date:     2019/6/26 13:09
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/26 13:09      1.0.0               创建
 */
package com.roboslyq.chains;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/6/26
 * @since 1.0.0
 */
public interface HasUpstreamPublisher<T> {
    Publisher<T> previous();
}