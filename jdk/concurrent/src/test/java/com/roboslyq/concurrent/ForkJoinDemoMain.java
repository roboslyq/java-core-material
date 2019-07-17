package com.roboslyq.concurrent;

import com.roboslyq.concurrent.ForkJoinPoolDemo;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Unit test for simple App.
 */
public class ForkJoinDemoMain
{
    @Test
    public void forkJoinPoolDemoTest() throws ExecutionException, InterruptedException {
        /*
         *此方式doug lea不推荐：   ForkJoinPool forkJoinPool = new ForkJoinPool();
         */
         ForkJoinPool forkJoinPool =  ForkJoinPool.commonPool();

        ForkJoinPoolDemo forkJoinPoolDemo = new ForkJoinPoolDemo(1,100);
            ForkJoinTask result =  forkJoinPool.submit(forkJoinPoolDemo);
         System.out.println(result.get());
        System.out.println(forkJoinPoolDemo.getRecursiveTimes());
    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        System.out.println("hello,world");
    }
}
