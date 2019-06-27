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

import java.util.function.Function;

/**
 *
 * 〈源节点〉:源节点稍与其他节点不一样，包含了数据源的创建
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public class MapPublisher<T,U> extends AbstractPublisherWithUpstream<T,U> {

    private Function<? super T, ? extends U> map;

    MapPublisher(Function<? super T, ? extends U> map, Publisher<T> previous) {
        super(previous);
        this.map = map;
    }

    @Override
    public void doSubscribe(Subscriber<? super U> subscriber) {
       previous.subscribe(new MapSubscriber(subscriber,this.map));
    }

   static final class MapSubscriber<T,U> extends  AbstractSubscriber<T,U> {
        final Function<? super T, ? extends U> map;
        MapSubscriber(Subscriber<? super U> downSubscriber, Function<T,U> map) {
            super(downSubscriber);
            this.map = map;
        }
        @Override
        public void onSubscribe(OnSubscribeProcessor var1) {
            System.out.println("onSubscribe...");
        }

        @Override
        public void onNext(T var1) {
           U var2 =  map.apply(var1);
            downSubscriber.onNext(var2);
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