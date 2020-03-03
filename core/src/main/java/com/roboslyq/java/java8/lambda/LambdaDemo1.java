/**
 * Copyright (C), 2015-2020
 * FileName: LambdaDemo1
 * Author:   luo.yongqian
 * Date:     2020/2/17 16:08
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/2/17 16:08      1.0.0               创建
 */
package com.roboslyq.java.java8.lambda;

import java.util.concurrent.TimeUnit;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @date 2020/2/17
 * @since 1.0.0
 */
public class LambdaDemo1 {
    public static void main(String[] args) {
        /*
         *传统写法
         */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("新线程1");
            }
        };
        runnable.run();

        Runnable runnable11 = ()-> System.out.println("新线程2");
        runnable11.run();

        while (true){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean func(String args1){
        //dosomething
        // return result
        return true;
    }
}