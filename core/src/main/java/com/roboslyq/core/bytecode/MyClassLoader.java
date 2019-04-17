package com.roboslyq.core.bytecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader{
    public  Class<?> findClass(String name) throws ClassNotFoundException {
        byte[]  clazzbyte =  null;
        String className = "com.roboslyq.core.bytecode." + name;
        try {
            String filePath = "core/target/classes/com/roboslyq/core/bytecode/" + name + ".class";
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            clazzbyte = fis.readAllBytes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(className, clazzbyte, 0, clazzbyte.length);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader();
        myClassLoader.findClass("com.roboslyq.core.bytecode.AsmTest");
    }
}
