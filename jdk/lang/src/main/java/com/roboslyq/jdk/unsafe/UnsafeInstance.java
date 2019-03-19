package com.roboslyq.jdk.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeInstance {

    public static void main(String[] args) throws IllegalAccessException,InstantiationException {
        try {
            Class<Unsafe> clazz = (Class<Unsafe>) Class.forName("sun.misc.Unsafe");
            Field field =  clazz.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            System.out.println(unsafe);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
