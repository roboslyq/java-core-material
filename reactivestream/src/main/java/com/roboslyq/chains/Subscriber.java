/**
 * Copyright (C), 2015-2019
 * FileName: Subscriber
 * Author:   luo.yongqian
 * Date:     2019/6/25 10:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * luo.yongqian         2019/6/2510:38      1.0.0               创建
 */
package com.roboslyq.chains;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public interface Subscriber<T> {

    void onSubscribe(OnSubscribeProcessor var1);

    void onNext( T var1);

    void onError( Throwable var1);

    void onComplete();
}