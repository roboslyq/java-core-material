/**
 * Copyright (C), 2015-2019
 * FileName: FluxDemo
 * Author:   luo.yongqian
 * Date:     2019/5/22 12:32
 * Description: fluxDEMO
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/22 12:32      1.0.0               创建
 */
package com.roboslyq.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Flow;

/**
 *   1、{@link Flux}中方法subscribe()有4个参数分别与{@link Flow.Subscriber}中的四个方法对应。
 *      一个是接口编程，一个是lambda编程。
 *   2、
 *   Flux.subscribe(
 *          @Nullable Consum e r<? s u per T> consumer,
 * 			@Nullable Consum e r<? s u per Throwable> errorConsumer,
 * 			@Nullable Runnable completeConsumer,
 * 			@Nullable Consum e r<? s u per Subscription> subscriptionConsumer)
 *
 *      参数1 consumer对应 Flow.Subscriber.onNext()
 *      参数2 errorConsumer 对应Flow.Subscriber.onError()
 *      参数3 completeConsumer 对应Flow.Subscriber.onError()
 *      参数4 subscriptionConsumer 对应Flow.Subscriber.onSubscribe()
 * 〈fluxDEMO〉
 * @author luo.yongqian
 * @create 2019/5/22
 * @since 1.0.0
 */
public class FluxDemo {

    /**
     * 同步线程（均为main线程），打印结果如下：
     * [ main ] : A
     * [ main ] : B
     * [ main ] : C
     */
    public static void synchronizedDemo(){
        Flux.just("A","B","C")
                .subscribe(FluxDemo::print);
    }

    /**
     * 1、publicOn方法为指定线程池，
     * 2、Flow线程池为Scheduler体系，与Java中的Executors基本一一对应
     * 3、同样有Schedulers工厂类，提供各种线程池初始化策略
     * 4、打印效果如下：
     * [ elastic-2 ] : A
     * [ elastic-2 ] : B
     * [ elastic-2 ] : C
     */
    @Test
    public static void asynchronizedDemo(){
        Flux.just("A","B","C")
                .parallel(2)
                .runOn(Schedulers.elastic())
                .subscribe(FluxDemo::print);
        try {
            //因为异步特性，此处模拟等待异常线程完成执行，否则控制台可以看不到打印信息
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     *  Flux.subscribe()方法重载
     */
    public static void subscribeLambda(){
        Flux.just("A","B","C")
                .subscribe(System.out::println);
    }

    public static void subscribeLambd1(){
        Flux.just("A","B","C")
                .subscribe(
                        //consumer
                        System.out::println
                        // error consumer
                        , System.out::println
                        );
    }
    public static void subscribeLambd2(){
        Flux.just("A","B","C")
                .subscribe(
                        //consumer-->
                        System.out::println
                        // error consumer -->
                        ,System.out::println
                        ,()->{
                            //completeConsumer
                            System.out.println("完成consumer");
                        }
                        );
    }
    public static void subscribeLambd3(){
        Flux.just("A","B","C")
                .subscribe(
                        //consumer
                        System.out::println
                        // error consumer
                        ,System.out::println
                        //completeConsumer
                        ,()->{
                            System.out.println("完成consumer");
                        }
                        //onNext
                        , subscription -> {
                            subscription.request(2);
                        }
                        );
    }

    private static void print(Object obj){
        String threadName = Thread.currentThread().getName();
        System.out.println("[ "+ threadName +" ] : " + obj);
    }

}