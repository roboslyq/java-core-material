package com.roboslyq.core.common;

public class ParentInterfaceImpl implements ParentInterface {
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }

    public String sayHello1() {
       return "hello world";
    }
}
