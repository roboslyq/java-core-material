package com.roboslyq.concurrent.aqs;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * 基于AQS实现独享锁功能
 */
public class ReentrantLockDemo {
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Integer count = 0;

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        for(int i = 0; i < 100 ; i++){
            new Thread(()->{
                for(int j = 0; j < 10 ; j++){
                    reentrantLockDemo.addCount();
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"thread-"+i).start();
        }
       new Scanner(System.in);
    }

    private void addCount(){
       reentrantLock.lock();
        int tmp = count;
        count += 1;
        println("成功获得锁，加1前的值：" + tmp +"加1后的值：" + count);
    //  reentrantLock.unlock();
    }

    private void println(Object obj){
        System.out.println("[当前线程：" + Thread.currentThread().getName() +" ] " + obj );
    }
    
    
}
