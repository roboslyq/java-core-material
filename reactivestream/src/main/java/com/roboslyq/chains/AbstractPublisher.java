package com.roboslyq.chains;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Publisher抽象类，实现共众的通用逻辑
 * @param <T>
 */
public abstract class AbstractPublisher<T> implements Publisher<T> {
    /**
     * Map方法实现
     * @param function 函数式接口转换实现
     * @return MapPublisher
     */
    final <R> Publisher map(Function<? super T, ? extends R> function){
        return new MapPublisher(function,this);
    }

    /**
     * Filter方法实现
     * @param filter
     * @return
     */
    AbstractPublisher filter(Predicate<T> filter){
        return  new FilterPublisher<T>(filter,this);
    }

    /**
     * 不同Publisher之间统一沟通入口。具体实现由抽象方法doDeal实现
     * @param subscriber
     */
    @Override
    public void subscribe(Subscriber<? super T> subscriber) {
        doSubscribe(subscriber);
    }

    public abstract void doSubscribe(Subscriber<? super T> subscriber);
}
