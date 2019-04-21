package com.roboslyq.core.bytecode.test;

import com.roboslyq.core.bytecode.javassit.JavassitService;
import com.roboslyq.core.classloader.LyqClassloader;
import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavassitTest {
    public static  void main(String[] args) throws Exception {
        JavassitService  jService = new JavassitService();
        jService.doService();
        //普通自定义加载器进行类加载
        LyqClassloader lyqClassloader =  new LyqClassloader();
        Class clazz =  lyqClassloader.loadClass("com.roboslyq.core.common.HelloWorldBean");
        Method methodC1 = clazz.getDeclaredMethod("sayHello");
        methodC1.invoke(clazz.getConstructor().newInstance());
    }
}
