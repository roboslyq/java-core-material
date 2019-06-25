package com.roboslyq.chains;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractProvider<T> implements Provider {
    public Provider map(Function function){
        return new NodeMap(function,this);
    }

    public AbstractProvider filter(Predicate filter){
        return  new NodeFilter(filter,this);
    }

    public void subscriber(Subscriber subscriber) {

    }

    @Override
    public void deal(Subscriber subscriber) {
        doDeal(subscriber);
    }

    public abstract void doDeal(Subscriber subscriber);
}
