package com.roboslyq.jdk.lang;

public class InterfaceTest implements InterfaceDemo {
    public static void main(String[] args) {
        InterfaceTest interfaceTest = new InterfaceTest();
        System.out.println( interfaceTest.getName1());
    }

    @Override
    public String getName() {
        return "nn";
    }
}
