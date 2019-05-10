/**
 * Copyright (C), 2015-2019
 * FileName: FirstByteBuddyMain
 * Author:   luo.yongqian
 * Date:     2019/5/9 14:54
 * Description: byte buddy的第一个DEMO
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/9 14:54      1.0.0               创建
 */
package com.roboslyq.core.bytebuddy;

import com.roboslyq.core.bytecode.bytebuddy.ByteBuddyService;
import com.roboslyq.core.bytecode.bytebuddy.Foo;
import com.roboslyq.core.common.ParentInterfaceImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 *
 * 〈byte buddy的第一个DEMO〉
 * @author luo.yongqian
 * @create 2019/5/9
 * @since 1.0.0
 */
public class FirstByteBuddyMain {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = ByteBuddyService.getClazz();
        ParentInterfaceImpl impl = (ParentInterfaceImpl)clazz.getConstructor().newInstance();
        System.out.println(impl);
        System.out.println(impl.sayHello1());
        Method method = clazz.getMethod("sayHello2");
        method.invoke(clazz,null);

        Class<?> clazz1 = ByteBuddyService.getClazz1();
        Object obj = clazz1.getConstructor().newInstance();
        System.out.println(obj.toString());

        Foo dynamicFoo = new ByteBuddy()
                .subclass(Foo.class)
                .method(isDeclaredBy(Foo.class)).intercept(FixedValue.value("One!"))
                .method(named("foo")).intercept(FixedValue.value("Two!"))
                .method(named("foo").and(takesArguments(1))).intercept(FixedValue.value("Three!"))
                .make()
                .load(FirstByteBuddyMain.class.getClassLoader())
                .getLoaded()
                .newInstance();
        System.out.println(dynamicFoo.foo());
        System.out.println(dynamicFoo.foo("1"));
    }




}

