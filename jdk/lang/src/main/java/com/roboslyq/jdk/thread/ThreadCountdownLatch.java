package com.roboslyq.jdk.thread;

import java.util.concurrent.CountDownLatch;

public class ThreadCountdownLatch {
    final static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {
        SimpleContainer simpleContainer = new SimpleContainer();
        Thread thread1 =  new Thread( ()->{
            for(int i=0;i<10;i++){
                simpleContainer.add(i);
                countDownLatch.countDown();
                System.out.println("add " + i + " data");
            }
            return;
        } );

        Thread thread2 =   new Thread( ()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("size == 5, finished!");
        } );
        thread1.start();
        thread2.start();
    }
}
