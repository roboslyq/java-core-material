package com.roboslyq.core.bytecode.test;

import com.roboslyq.core.bytecode.cglib.CallbackFilterService;
import com.roboslyq.core.bytecode.cglib.MethodInterceptorService;
import com.roboslyq.core.bytecode.cglib.TargetResultFixed;
import com.roboslyq.core.common.HelloWorldBean;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class CglibCallbackFilterTest {


    public static void main(String[] args) {
        Enhancer enhancer =new Enhancer();
        //参数为需要被代理的类
        // （使用继承关系实现代理，故父类不需要实现 接口但不能代理final类，因为final类不能被继承）
        enhancer.setSuperclass(HelloWorldBean.class);
        CallbackFilterService cfilter = new CallbackFilterService();
        /**
         * (1)callback1：方法拦截器
         (2)NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
         (3)FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
         */
        Callback noopCb = NoOp.INSTANCE;
        Callback callback1 = new MethodInterceptorService();
        Callback fixedValue = new TargetResultFixed();
        Callback[] cbarray = new Callback[]{callback1,callback1,noopCb,fixedValue};
        enhancer.setCallbacks(cbarray);
        enhancer.setCallbackFilter(cfilter);

        //参数为代理类,setCallback与覆盖上面的setCallbacks。所以此处不能调用
//        enhancer.setCallback(new CglibMethodInterceptorTest());
        HelloWorldBean targetObject=(HelloWorldBean)enhancer.create();
        targetObject.sayHello();
    }
}
