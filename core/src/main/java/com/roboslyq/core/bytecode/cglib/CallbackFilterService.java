package com.roboslyq.core.bytecode.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class CallbackFilterService implements CallbackFilter {

    /**
     * 过滤方法
     * 返回的值为数字，代表了Callback数组中的索引位置，要到用的Callback
     */
    @Override
    public int accept(Method method) {
        if(method.getName().equals("sayHello")){
            System.out.println("filter method ==sayHello");
            return 0;
        } if(method.getName().equals("sayHello1")){
            System.out.println("filter method ==sayHello1");
            return 1;
        }if(method.getName().equals("sayHello2")){
            System.out.println("filter method ==sayHello2");
            return 1;
        }
        System.out.println("filter method == 未知:"+method.getName());
        return 1;
    }
}
