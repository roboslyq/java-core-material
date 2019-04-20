package com.roboslyq.core.classloader;

/**
 * 类加载器，直接加载字节码
 */
public class DynamicClassLoader extends ClassLoader {
    public Class<?> defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}