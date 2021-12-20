//package com.roboslyq.core.bytecode.test;
//
//import com.roboslyq.core.bytecode.asm.AsmService;
//import com.roboslyq.core.bytecode.asm.ICalculator;
//import com.roboslyq.core.classloader.LyqClassloader;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//public class AsmTest {
//    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        String asmClassName = "com/roboslyq/core/bytecode/AsmTest";
//        String className = "com.roboslyq.core.bytecode.AsmTest";
//        AsmService asmService = new AsmService();
//        asmService.doAsm(asmClassName);
//        ClassLoader classLoader = new LyqClassloader();
//        Class clazz = classLoader.loadClass(className);
//        Method[] methodArray = clazz.getDeclaredMethods();
//        for (Method method : methodArray) {
//            System.out.println("方法名称  --> " + method.getName());
//        }
//        ICalculator calc = (ICalculator)clazz.getConstructor().newInstance();
//        System.out.println("2 + 2 = " + calc.add(2, 2));
//        //运行特定的方法compareTo
//        Method compareTo = clazz.getMethod("add",Integer.TYPE,Integer.TYPE);
//        Object result = compareTo.invoke(clazz.getConstructor().newInstance(),2,3);
//        System.out.println("2 + 3 = " + result);
//    }
//}
//
