package com.roboslyq;

import java.util.*;

public class TestMain {
    private static boolean initialized = false;
    private static final long MILLIS_PER_DAY
            = 24 * 60 * 60 * 1000;

    private static final long MICROS_PER_DAY
            = 24 * 60 * 60 * 1000 * 1000;
    static {
        print(2);
        Thread t = new Thread(() ->{
           // initialized = true;
            //print(3);
            } );
        print(4);
        t.start();
        print(5);
        try {
            t.join();
            print(6);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
    public static void main(String[] args) {
//        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
//        int t1 = 24 * 60 * 60;
//        int t2 =  24 * 60 * 60 * 1000;
//        long t3 = (24 * 60 * 60 * 1000 * 1000)& 0x00000000ffffffff ;
//        int t4 =  24 * 60 * 60 * 1000 * 1000;
//        long t5 = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000001;
//        long t6 = 0b00000000_00000000_00000000_00000000_10000000_00000000_00000000_00000001;
//        int t7 = (int) t5;
//        int t8 = (int) t6;
//        System.out.println(t3);
//        System.out.println(t4);
//        System.out.println(24 * 60 * 60 * 1000 * 1000L);
//        System.out.println(MILLIS_PER_DAY);
//        System.out.println(MICROS_PER_DAY);
//        System.out.println(t7);
//        System.out.println(t8);
  /*      final String pig = "length: 10";
        final String dog = "length: " + pig.length();
        final String pig2 = "length: 10";
        System.out.println("Animals are equal: "
                + pig == dog);
        System.out.println("Animals are equal: "
                + pig == pig2);*/
        print(1);
        System.out.println(initialized);
    }


    public static void print(Object o){
        System.out.println("current thread: " + Thread.currentThread().getId() + "-->" + o);
    }
}

