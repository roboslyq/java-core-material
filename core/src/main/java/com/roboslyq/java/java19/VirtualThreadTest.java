package com.roboslyq.java.java19;

import java.io.IOException;

/**
 * @author roboslyq
 * @desc Java19虚拟线程测试
 * @since 2022/10/9
 */
public class VirtualThreadTest {
    public static void main(String[] args) throws IOException {
        Thread.ofVirtual().start(() -> {
            System.out.println("hello ,jdk19 virtual thread!!!");
        });
//        initVirtualThread(1000);
        initThread(1000);
        System.in.read();
    }

    public static void initVirtualThread(int count) {
        for (int i = 0; i < count; i++) {
            Thread.ofVirtual().start(() -> {
                while (true) {
                    try {
                        System.out.println("hello ,jdk19 virtual thread!!!,is virtual: " + Thread.currentThread().isVirtual());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }


    public static void initThread(int count) {
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        System.out.println("hello ,jdk19 virtual thread!!!,is virtual: " + Thread.currentThread().isVirtual());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();
        }
    }
}
