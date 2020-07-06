package com.roboslyq.core.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author roboslyq
 * @Desc
 * @create 2020-06-30 22:37
 * @since 1.0
 */
public class CommonReflectImpl implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("通用操作，只有接口没有实现");
        return "代理成功";
    }
}
