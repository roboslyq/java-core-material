package com.roboslyq.jdk.lang;


import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    public static void main(String[] args) {
       int a = 126;
       Integer b =  Integer.valueOf("126");
       Integer c =  Integer.valueOf("126");

       int d = 129;
       Integer e =  Integer.valueOf("129");
       Integer f =  Integer.valueOf("129");

        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(c));
        System.out.println(System.identityHashCode(getV("126")));
        System.out.println(System.identityHashCode(d));
        System.out.println(System.identityHashCode(e));
        System.out.println(System.identityHashCode(f));
        System.out.println(System.identityHashCode(getV("129")));
    }
    public static Integer getV(String str){
        return  Integer.valueOf(str);
    }
}
