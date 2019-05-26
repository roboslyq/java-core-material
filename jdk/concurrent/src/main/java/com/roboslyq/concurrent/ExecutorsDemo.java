package com.roboslyq.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo {
    public static void main(String[] args) {
        print("hello main;"  );
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() ->{
            print("hello，fixed Thread poll");
        });
        //线程池会永远阻塞，不会结束，队非手动shutDown()。
        executorService.shutdown();
    }
    private static void print(Object object){
        System.out.println("【当前线程 : " + Thread.currentThread().getName() +"  ] : " + object  );
    }
}
