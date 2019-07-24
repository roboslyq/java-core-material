/**
 * Copyright (C), 2015-2019
 * FileName: ForkJoinPoolDemo
 * Author:   luo.yongqian
 * Date:     2019/5/27 12:56
 * Description: ForkJoin框架DEMO
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/27 12:56      1.0.0               创建
 */
package com.roboslyq.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 〈ForkJoin框架DEMO〉,建议所有ForkJoin框架不要直接继承ForkJoinTask,而是继承相关子类：
 *      RecursiveTask：有返回值
 *      RecursiveAction:没有返回值
 * @author luo.yongqian
 * @create 2019/5/27
 * @since 1.0.0
 */
public class ForkJoinPoolDemo extends RecursiveTask<Integer> {
    private Integer startIndex;
    private Integer endIndex;
    //定义为类变量，共享
    private static Integer recursiveTimes = 0;

    public ForkJoinPoolDemo(Integer startIndex, Integer endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    /**
     * 重写compute方法，此方法主要有一个固定模式（判断是否需要继承拆分任务）：
     * 1、如果不需要继承拆分任务，则直接进行计算，然后返回相关值。
     * 2、如果任务粒度不够细，继承拆分，然后调用新任务的fork方法，并且最后合并返回相关的join方法对应的结果。
     * @return
     */
    @Override
    protected Integer compute() {
        recursiveTimes++;
        int TASK_SIZE = 10;
        println("---------------------------- " + recursiveTimes + " ----------------------------");
        println("startIndex -->" + startIndex);
        println("endIndex -->" + endIndex);
        //分支条件一：任务已经小于设定值，不需要拆分直接计算
        if(endIndex - startIndex <= TASK_SIZE){
            println("开始计算... ...");
            int tmp = 0 ;
            for(int i = startIndex; i <= endIndex ; i++){
                tmp += i;
            }
            return tmp;
        }else{//分支条件二:任务不够小继续拆分
            println("继续拆分");
            ForkJoinPoolDemo task1 = new ForkJoinPoolDemo(startIndex,(endIndex + startIndex)/2);
            task1.fork();
            ForkJoinPoolDemo task2 = new ForkJoinPoolDemo(1 + (endIndex + startIndex)/2,endIndex);
            task2.fork();
            return task1.join() + task2.join();
        }
    }

    public Integer getRecursiveTimes() {
        return recursiveTimes;
    }
    private void println(Object obj){
        System.out.println("[ 当前线程: " + Thread.currentThread().getName() + " ] " + obj);
    }

}

