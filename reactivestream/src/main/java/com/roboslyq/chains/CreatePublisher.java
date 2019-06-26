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
public class CreatePublisher extends AbstractPublisher {

    OnSubscribeProcessor processor;
    public Publisher previous;
    public CreatePublisher(OnSubscribeProcessor processor) {
        this.processor = processor;
    }
    @Override
    public void doDeal(Subscriber subscriber) {
        NodeCreateSubscriber subscriberCreate =    new NodeCreateSubscriber(subscriber);
        processor.doSubscribe(subscriberCreate);
    }

    class NodeCreateSubscriber implements Subscriber{
        OnSubscribeProcessor processor;
        private Subscriber downSubscriber;
        public NodeCreateSubscriber(Subscriber downSubscriber) {
            this.downSubscriber = downSubscriber;
        }
        @Override
        public void onSubscribe(OnSubscribeProcessor var1) {
            System.out.println("onSubscribe...");
        }

        @Override
        public void onNext(Object var1) {
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