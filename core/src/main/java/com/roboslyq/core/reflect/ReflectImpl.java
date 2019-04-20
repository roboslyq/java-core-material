package com.roboslyq.core.reflect;

import com.roboslyq.core.common.ParentInterface;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ReflectImpl implements InvocationHandler, Serializable {

    private ParentInterface parentInterface;

    public ReflectImpl(ParentInterface parentInterface) {
        this.parentInterface = parentInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object o = method.invoke(parentInterface,args);
        System.out.println("after");
        return o;
    }
}
