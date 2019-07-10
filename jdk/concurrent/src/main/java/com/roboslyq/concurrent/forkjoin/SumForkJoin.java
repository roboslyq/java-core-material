package com.roboslyq.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin框架简单使用示例
 */
public class SumForkJoin extends RecursiveTask<Integer> {
    private final static Integer TOTAL_NUM = 100;
    private final static Integer TASK_SIZE  = 20;

    public static void main(String[] args) {
        SumForkJoin sumFormJoin = new SumForkJoin(SumForkJoin.initArray(TOTAL_NUM),0,TOTAL_NUM);
        sumFormJoin.forEach();
        sumFormJoin.doSum();

    }
    Integer[] array;
    Integer left;
    Integer right;
    public SumForkJoin(Integer[] array, Integer left, Integer right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }


    @Override
    protected Integer compute() {
        System.out.println("[current thread : ]" + Thread.currentThread().getId());
        Integer currentSize = this.right - this.left;
        if(currentSize <= TASK_SIZE){
            int tmp = 0;
            for(int i= left ;i< right;i++){
                tmp += array[i];
            }
            return  tmp;
        }else{
            Integer middle =  (this.right + this.left )/ 2;
            SumForkJoin task1 = new SumForkJoin(this.array,this.left,middle);
            SumForkJoin task2 = new SumForkJoin(this.array,middle,right);
            task1.fork();
            task2.fork();
            Integer res1 = task1.join();
            return  res1 + task2.join();
        }
    }
    public static Integer[] initArray(Integer arraySize){
        Integer[]  array  = new Integer[arraySize];
        for(int val = 0;val < arraySize ;val++){
            array[val] = val + 1;
        }
        return array;
    }

    public void forEach(){
        Long startTime = System.nanoTime();
        Integer temp = 0;
        Integer arraySize = array.length;
        for(int i = 0 ;i<arraySize ;i++){
            temp += array[i];
        }
        System.out.println(temp);
        Long endTime = System.nanoTime();
        System.out.println("for循环用时：" + (endTime - startTime));
    }
    public void doSum(){
        Long startTime = System.nanoTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask future = forkJoinPool.submit(this);
        if (future.isCompletedAbnormally()) {
            System.out.println(future.getException());
        }
        try {
            System.out.println("result: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Long endTime = System.nanoTime();
        System.out.println("ForkJoin用时：" + (endTime - startTime));
    }
}
