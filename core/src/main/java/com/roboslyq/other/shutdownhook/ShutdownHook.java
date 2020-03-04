/**
 * Copyright (C), 2015-2020
 * FileName: ShutdownHook
 * Author:   luo.yongqian
 * Date:     2020/3/4 16:08
 * Description: 优雅停机
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/4 16:08      1.0.0               创建
 */
//package com.roboslyq.other.shutdownhook;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.TimeUnit;

/**
 *
 * 〈优雅停机〉
 * @author luo.yongqian
 * @date 2020/3/4
 * @since 1.0.0
 */
public class ShutdownHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new DemoHook());
        printProcessId();
        for (;;){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printProcessId(){
        RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(mxBean.getName().split("@")[0]);
    }

}

class DemoHook extends Thread{
    @Override
    public void run(){
        System.out.println("优雅停机");
    }
}