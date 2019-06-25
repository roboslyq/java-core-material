/**
 * Copyright (C), 2015-2019
 * FileName: AbstractSubscriber
 * Author:   luo.yongqian
 * Date:     2019/6/25 17:13
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/25 17:13      1.0.0               创建
 */
package com.roboslyq.chains;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public abstract class AbstractSubscriber<T,R> implements Subscriber {

    protected final Subscriber<? super R> downSubscriber;

    public AbstractSubscriber(Subscriber<? super R> downSubscriber) {
        this.downSubscriber = downSubscriber;
    }

    @Override
    public void onSubscribe(OnSubscribeProcessor var1) {
        System.out.println("onSubscribe...");
    }

    @Override
    public void onError(Throwable var1) {
        System.out.println("Throwable...");

    }

    @Override
    public void onComplete() {
        System.out.println("onComplete...");

    }
}