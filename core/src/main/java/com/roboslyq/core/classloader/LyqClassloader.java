package com.roboslyq.core.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LyqClassloader extends ClassLoader {
    private final static String CLASS_PATH_PREFIX = "core/target/classes/";
    @Override
    protected Class<?> findClass(String name)  {
        byte[] bytes = null;
        try {
            bytes = doFindClassFromLocalFile(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //此方法已经过时了
//        return defineClass(bytes,0,bytes.length);
        //此方法为替代方法，强调已经知道类的名称"com.roboslyq.core.classloader.ClassloaderHello"
        return defineClass(name,bytes,0,bytes.length);
    }
    public byte[] doFindClassFromLocalFile(String name) throws IOException {
        File file = new File(CLASS_PATH_PREFIX + getFilePath(name));
        System.out.println(file.getAbsolutePath());
        FileInputStream fileInputStream = new FileInputStream(file);
        return fileInputStream.readAllBytes();
    }
    public static void main(String[] args) {
        LyqClassloader myClassloader = new LyqClassloader();
        try {
            Class clazz =  myClassloader.loadClass("com.roboslyq.core.classloader.ClassloaderHello");
            Class clazz1 = myClassloader.loadClass("com.roboslyq.core.bytecode.asm.AsmTest");
            System.out.println(clazz1);
          System.out.println(clazz);
          Method method = clazz.getMethod("sayHello");
            try {
                method.invoke(clazz.getConstructor().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath(String classPath){
        return classPath.replaceAll("\\.","/") + ".class";
    }
}
