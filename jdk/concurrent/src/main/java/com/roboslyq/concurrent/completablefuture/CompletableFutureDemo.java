/**
 * Copyright (C), 2015-2019
 * FileName: CompletableFutureDemo
 * Author:   luo.yongqian
 * Date:     2019/7/24 12:49
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/7/24 12:49      1.0.0               创建
 */
package com.roboslyq.concurrent.completablefuture;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/7/24
 * @since 1.0.0
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture res =  CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("runable");
            }
        }).thenApplyAsync(val ->{
            System.out.println("thel : " + val);
            return 1 + 1;
        }).thenApply(val ->{
            System.out.println(val + 1);
            return val + 1;
        }).thenCombineAsync(//与另一个CompletionStage结果合并
                CompletableFuture.supplyAsync(()->{return 4 ;})//另一个CompletionStage
                ,(val1,val2) -> val1 * val2  //合并结果的lambda
        )
                ;
        System.out.println("res:" + res.get());
        try {
            System.in.read();
        } catch (IOException e) {

        }
    }

}