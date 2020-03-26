/**
 * Copyright (C), 2015-2020
 * FileName: FluxParallelDemo
 * Author:   luo.yongqian
 * Date:     2020/3/17 17:53
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/17 17:53      1.0.0               创建
 */
package com.roboslyq.reactor;

import com.roboslyq.Log;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @date 2020/3/17
 * @since 1.0.0
 */
public class FluxParallelDemo {

    /**
     * 同步线程（均为main线程），打印结果如下：
     * [ main ] : A
     * [ main ] : B
     * [ main ] : C
     */
    @Test
    public void synchronizedDemo(){
        Flux.just("A","B","C")
                .subscribe(Log::print);
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
    public void asynchronizedDemo(){
        Flux.just("A","B","C")
                //指定并发线程数
                .parallel(2)
                //指定运行具体的线程池，常见有Schedulers.elastic(),Schedulers.immediate(),Schedulers.parallel()
                .runOn(Schedulers.elastic())
                .subscribe(Log::print);
        try {
            //因为异步特性，此处模拟等待异常线程完成执行，否则控制台可以看不到打印信息
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}