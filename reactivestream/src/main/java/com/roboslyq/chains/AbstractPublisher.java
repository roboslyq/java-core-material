package com.roboslyq.chains;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractPublisher<T> implements Publisher {
    public Publisher map(Function function){
        return new MapPublisher(function,this);
    }

    public AbstractPublisher filter(Predicate filter){
        return  new FilterPublisher(filter,this);
    }

    public void subscriber(Subscriber subscriber) {

    }

    @Override
    public void deal(Subscriber subscriber) {
        doDeal(subscriber);
    }

    public abstract void doDeal(Subscriber subscriber);
}
