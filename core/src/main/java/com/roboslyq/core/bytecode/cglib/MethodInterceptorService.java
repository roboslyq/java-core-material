package com.roboslyq.core.bytecode.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodInterceptorService implements MethodInterceptor {
    /**
     * 所以通用的代理方法入口类，用方法替代原方法的调用。
     * 原方法可以可能普通反射调用或者MethodProxy调用，其中MethodProxy调用会有更好的性能。
     * @param obj "this"：当前代理类（即加强的类）
     * @param method intercepted Method : 被代理方法
     * @param args argument array：参数数组，封装了基本类型
     * @param proxy ：调用父类
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        doBefore();
        //自己调用自己，一直死循环，直到stack over memerry
        //proxy.invoke(obj,args);
        proxy.invokeSuper(obj,args);
        doAfter();
        return null;
    }
    public void doBefore(){
        System.out.println("before doing ...");
    }
    public void doAfter(){
        System.out.println("after doing ...");
    }
}
