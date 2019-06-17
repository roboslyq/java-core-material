package com.roboslyq.java.java9.methodhandle;

import com.roboslyq.java.ModelDemo;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class MethodHandlesDemo {
    private static final VarHandle VALUE;
    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            VALUE = l.findVarHandle(ModelDemo.class, "id", int.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(1 << 30);
        MethodHandlesDemo demo = new MethodHandlesDemo();
        boolean result = VALUE.compareAndSet(demo, 0, 10);
        System.out.println(result);
    }
}
