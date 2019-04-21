package com.roboslyq.core.bytecode.test;

import com.roboslyq.core.bytecode.cglib.MethodInterceptorService;
import com.roboslyq.core.common.HelloWorldBean;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author roboslyq
 */
public class CglibMethodInterceptorTest {

    public static void main(String args[]) {
        Enhancer enhancer =new Enhancer();
        //参数为需要被代理的类
        // （使用继承关系实现代理，故父类不需要实现 接口但不能代理final类，因为final类不能被继承）
        enhancer.setSuperclass(HelloWorldBean.class);
        //参数为代理类
        enhancer.setCallback(new MethodInterceptorService());
        HelloWorldBean targetObject=(HelloWorldBean)enhancer.create();
        targetObject.sayHello();
    }
}
