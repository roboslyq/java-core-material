//package com.roboslyq.core.classloader;
//
//import java.lang.reflect.Method;
//
///**
// * LyqClassloader测试类
// */
//public class LyqClassloaderTest {
//    public static void main(String[] args) {
//        LyqClassloader lyqClassloader = new LyqClassloader();
//        try {
//            Class clazz =  lyqClassloader.loadClass("com.roboslyq.core.common.HelloWorldBean");
//            Method methodC1 = clazz.getDeclaredMethod("sayHello");
//            methodC1.invoke(clazz.getConstructor().newInstance());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
