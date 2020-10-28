/**
 * Copyright (C), 2015-2020
 * FileName: MyMethodInterceptor
 * Author:   luo.yongqian
 * Date:     2020/10/28 16:26
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/10/28 16:26      1.0.0               创建
 */
package com.roboslyq.cglib;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/10/28
 * @since 1.0.0
 */
import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor{

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("这里是对目标类进行增强！！！");
        //注意这里的方法调用，不是用反射哦！！！
        Object object = proxy.invokeSuper(obj, args);
        return object;
    }
}