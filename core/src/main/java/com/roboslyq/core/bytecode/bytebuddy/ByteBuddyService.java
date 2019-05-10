/**
 * Copyright (C), 2015-2019
 * FileName: ByteBuddyService
 * Author:   luo.yongqian
 * Date:     2019/5/9 15:02
 * Description: 第一个byteBuddy服务实现
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/9 15:02      1.0.0               创建
 */
package com.roboslyq.core.bytecode.bytebuddy;

import com.roboslyq.core.common.ParentInterfaceImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 *
 * 〈第一个byteBuddy服务实现〉
 * @author luo.yongqian
 * @create 2019/5/9
 * @since 1.0.0
 */
public class ByteBuddyService<T> {
    /**
     * 第一种场景：继承父类，创建一个新类
     * @return
     */
    public static Class<?> getClazz(){

     return new ByteBuddy()
                .subclass(ParentInterfaceImpl.class)
                .method(named("sayHello1")).intercept(FixedValue.value("Hello,byte buddy!"))
//                .defineMethod("sayHello2",String.class,null).intercept(FixedValue.value("Hello,byte buddy! new method!!!"))
                .make()
                .load(ByteBuddyService.class.getClassLoader())
                .getLoaded()
                ;
    }
    /**
     * 第一种场景：创建一个新类
     * @return
     */
    public static Class<?> getClazz1(){

        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(ByteBuddyService.class.getClassLoader())
                .getLoaded();
        return dynamicType;
    }
}