/**
 * Copyright (C), 2015-2019
 * FileName: OnSubscribeProcessor
 * Author:   luo.yongqian
 * Date:     2019/6/25 17:11
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/25 17:11      1.0.0               创建
 */
package com.roboslyq.chains;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public interface OnSubscribeProcessor {
    void doSubscribe(Subscriber subscriber);
}