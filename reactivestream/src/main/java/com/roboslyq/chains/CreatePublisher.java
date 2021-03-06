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

/**
 *
 * 〈源节点〉:源节点稍与其他节点不一样，包含了数据源的创建
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public class CreatePublisher<T> extends AbstractPublisher<T> {

    private OnSubscribeProcessor<T> processor;

    CreatePublisher(OnSubscribeProcessor<T> processor) {
        this.processor = processor;
    }

    /**
     * doDeal方法具体实现，因为是Create，所以没有前驱节点。直接发起后置节点调用
     * @param subscriber
     */
    @Override
    public void doSubscribe(Subscriber<? super T> subscriber) {
        NodeCreateSubscriber subscriberCreate =    new NodeCreateSubscriber(subscriber);
        processor.doSubscribe(subscriberCreate);
    }

    class NodeCreateSubscriber implements Subscriber<T>{

        private Subscriber<? super T> downSubscriber;

        NodeCreateSubscriber(Subscriber<? super T> downSubscriber) {
            this.downSubscriber = downSubscriber;
        }
        @Override
        public void onSubscribe(OnSubscribeProcessor<T> var1) {
            System.out.println("onSubscribe...");
        }

        @Override
        public void onNext(T var1) {
            downSubscriber.onNext(var1);
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