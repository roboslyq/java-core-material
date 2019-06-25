/**
 * Copyright (C), 2015-2019
 * FileName: NodeCreate
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
 * 〈源节点〉:源节点稍与其他节点不一样，包含了数据源的创建
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public class NodeFilter<T>  extends AbstractProvider  {
    public Predicate filter;
    public Provider previous;
    public NodeFilter(Predicate filter,Provider previous) {
        this.previous = previous;
        this.filter = filter;
    }
    @Override
    public void doDeal(Subscriber subscriber) {
        this.previous.deal(new NodeFilterSubscriber(subscriber,filter));
    }

    class NodeFilterSubscriber extends  AbstractSubscriber{
        public Predicate predicate;
        public NodeFilterSubscriber(Subscriber downSubscriber,Predicate predicate) {
            super(downSubscriber);
            this.predicate = predicate;
        }
        @Override
        public void onSubscribe(OnSubscribeProcessor var1) {
            System.out.println("onSubscribe...");
        }

        @Override
        public void onNext(Object var1) {
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