/**
 * Copyright (C), 2015-2020
 * FileName: AsmTest
 * Author:   luo.yongqian
 * Date:     2020/10/28 16:47
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/10/28 16:47      1.0.0               创建
 */
package com.roboslyq.cglib.asm;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/10/28
 * @since 1.0.0
 */
public class AsmTest {
    public static void main(String[] args) throws Exception {
        //生成字节码
        byte[] byteCodes = HelloWorldDump.dump();
        // 根据字节码获得Class对象
        Class<?> clazz = AsmUtils.defineClass("com.roboslyq.cglib.asm.HelloWorld1", byteCodes, Thread.currentThread().getContextClassLoader());
        // 由于是生成的Class，所以只能通过反射调用方法。
        Method method = clazz.getMethod("sayHello");
        method.invoke(clazz.newInstance());
    }

}