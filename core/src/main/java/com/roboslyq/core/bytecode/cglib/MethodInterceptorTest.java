package com.roboslyq.core.bytecode.cglib;

import com.roboslyq.core.bytecode.HelloWorldBean;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author roboslyq
 */
public class MethodInterceptorTest implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib调用前处理");
        Object result = methodProxy.invokeSuper(o,objects);//调用被代理类的方法
        System.out.println("cglib调用后处理");
        return result;
    }
    public static void main(String args[]) {
        Enhancer enhancer =new Enhancer();
        //参数为需要被代理的类
        // （使用继承关系实现代理，故父类不需要实现 接口但不能代理final类，因为final类不能被继承）
        enhancer.setSuperclass(HelloWorldBean.class);
        //参数为代理类
        enhancer.setCallback(new MethodInterceptorTest());
        HelloWorldBean targetObject=(HelloWorldBean)enhancer.create();
        targetObject.sayHello();
    }
}
