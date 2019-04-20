package com.roboslyq.core.bytecode.javassit;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavassitTest {
    public static void main(String[] args) throws Throwable {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.roboslyq.core.common.HelloWorldBean");
        CtMethod m = cc.getDeclaredMethod("sayHello");
        m.insertBefore("System.out.println(\"before=------\");");
        m.insertAfter("System.out.println(\"after=------\");");
        cc.setSuperclass(pool.get("com.roboslyq.core.common.BytecodeParent"));
        cc.writeFile("D://HelloWorldBean.class");

        Class clazz = cc.toClass();
        Method methodC = clazz.getDeclaredMethod("sayHello");
        methodC.invoke(clazz.getConstructor().newInstance());

        Loader cl = new Loader(pool);
        Class c = cl.loadClass("com.roboslyq.core.common.HelloWorldBean");
        Method methodC1 = c.getDeclaredMethod("sayHello");
        methodC1.invoke(c.getConstructor().newInstance());

    }
}
