package com.roboslyq.core.bytecode.cglib;

import com.roboslyq.core.common.HelloWorldBean;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

public class CallbackFilterTest implements CallbackFilter {
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

    public static void main(String[] args) {
        Enhancer enhancer =new Enhancer();
        //参数为需要被代理的类
        // （使用继承关系实现代理，故父类不需要实现 接口但不能代理final类，因为final类不能被继承）
        enhancer.setSuperclass(HelloWorldBean.class);
        CallbackFilterTest cfilter = new CallbackFilterTest();
        /**
         * (1)callback1：方法拦截器
         (2)NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
         (3)FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
         */
        Callback noopCb= NoOp.INSTANCE;
        Callback callback1=new MethodInterceptorTest();
        Callback fixedValue=new TargetResultFixed();
        Callback[] cbarray =new Callback[]{callback1,callback1,noopCb,fixedValue};
        enhancer.setCallbacks(cbarray);
        enhancer.setCallbackFilter(cfilter);

        //参数为代理类
//        enhancer.setCallback(new MethodInterceptorTest());
        HelloWorldBean targetObject=(HelloWorldBean)enhancer.create();
        targetObject.sayHello();
    }
}
