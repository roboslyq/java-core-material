package com.roboslyq.core.bytecode.javassit;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Loader;

import java.lang.reflect.Method;

public class JavassitService {
    public void doService() throws Exception {
        //创建ClassPool
        ClassPool pool = ClassPool.getDefault();
        //获取对应的类
        CtClass cc = pool.get("com.roboslyq.core.common.HelloWorldBean");
        //获取对应的方法
        CtMethod m = cc.getDeclaredMethod("sayHello");
        //修改字节码（Java语言层面的语法，可以结合template(模板，例如velocity,jsp,thymleaf等)）
        //十分方便的实现扩展，AOP编程
        m.insertBefore("System.out.println(\"before=------\");");
        m.insertAfter("System.out.println(\"after=------\");");
        //设置父类
        cc.setSuperclass(pool.get("com.roboslyq.core.common.BytecodeParent"));
        //生成Class文件
        cc.writeFile("/core/targe/classes/");
        //通过Javassit直接获取对象
        loadClass(cc);
        //通过
        loadClass(pool);
    }

    public void loadClass(CtClass cc) throws Exception {
        //通过Javassit直接获取对象
        Class clazz = cc.toClass();
        Method methodC = clazz.getDeclaredMethod("sayHello");
        methodC.invoke(clazz.getConstructor().newInstance());
    }

    public void loadClass(ClassPool pool) throws Exception {
        Loader cl = new Loader(pool);
        Class c = cl.loadClass("com.roboslyq.core.common.HelloWorldBean");
        Method methodC1 = c.getDeclaredMethod("sayHello");
        methodC1.invoke(c.getConstructor().newInstance());
    }
}
