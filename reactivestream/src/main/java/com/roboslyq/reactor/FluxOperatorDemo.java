/**
 * Copyright (C), 2015-2020
 * FileName: FluxDemo2
 * Author:   luo.yongqian
 * Date:     2020/3/5 16:39
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/5 16:39      1.0.0               创建
 */
package com.roboslyq.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * 〈〉
 *
 * @author luo.yongqian
 * @date 2020/3/5
 * @since 1.0.0
 */
public class FluxOperatorDemo {

    /**
     *  Flux.subscribe()方法重载
     */
    @Test
    public void subscribeLambda(){
        Flux.just("A","B","C")
                .subscribe(System.out::println);
    }

    @Test
    public void subscribeLambd1(){
        Flux.just("A","B","C")
                .subscribe(
                        //consumer
                        System.out::println
                        // error consumer
                        , System.out::println
                );
    }
    
    @Test
    public void subscribeLambd2(){
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
    @Test
    public void subscribeLambd3(){
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
}



