package com.roboslyq.jdk.lang;

public interface InterfaceDemo {
    public String getName();

    default String getName1(){
        return getName();
    }
}
