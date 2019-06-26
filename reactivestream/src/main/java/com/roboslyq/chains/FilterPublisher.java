/**
 * Copyright (C), 2015-2019
 * FileName: CreatePublisher
 * Author:   luo.yongqian
 * Date:     2019/6/25 16:59
 * Description: 源节点
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/25 16:59      1.0.0               创建
 */
package com.roboslyq.chains;

import java.util.function.Predicate;

/**
 *
 * 过滤器处理
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public class FilterPublisher<T>  extends AbstractHasUpstreamPublisher<T,T> {
     private Predicate<T> filter;

     FilterPublisher(Predicate<T> filter, Publisher<T> previous) {
         super(previous);
        this.filter = filter;
    }

    @Override
    public void doSubscribe(Subscriber<? super T> subscriber) {
        this.previous.subscribe(new NodeFilterSubscriber(subscriber,filter));
    }

    class NodeFilterSubscriber extends  AbstractSubscriber<T,T>{
        Predicate<? super T> predicate;
        NodeFilterSubscriber(Subscriber<? super T> downSubscriber, Predicate<T> predicate) {
            super(downSubscriber);
            this.predicate = predicate;
        }
        @Override
        public void onSubscribe(OnSubscribeProcessor var1) {
            System.out.println("onSubscribe...");
        }

        @Override
        public void onNext(T var1) {
            if (predicate.test(var1)) {
                System.out.println("符合filter条件，往下传播");
                downSubscriber.onNext(var1);
            }else{
                System.out.println("不符合filter条件");
            }
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
}