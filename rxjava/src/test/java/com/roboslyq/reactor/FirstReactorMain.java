/**
 * Copyright (C), 2015-2019
 * FileName: FirstReactorMain
 * Author:   luo.yongqian
 * Date:     2019/5/6 14:26
 * Description: 第一个reactor示例
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/6 14:26      1.0.0               创建
 */
package com.roboslyq.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * 〈第一个reactor示例〉
 * @author luo.yongqian
 * @create 2019/5/6
 * @since 1.0.0
 */
public class FirstReactorMain {
    public static void main(String[] args) {
//        simpleFluxByStaticMethod();
        fluxGenerate();
        fluxCreate();
    }

    /**
     * 通过 Flux 类的静态方法创建 Flux 序列
     */
    public static void simpleFluxByStaticMethod(){
        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
//        Flux.intervalMillis(1000).subscribe(System.out::println);
    }
    /**
     * 使用 generate()方法生成 Flux 序列
     */
    public static void fluxGenerate(){
        Flux.generate(sink -> {
            sink.next("hello world1");
            sink.complete();
        }).subscribe(System.out :: println);

        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }

    /**
     * 使用 create()方法生成 Flux 序列
     */
    public static void fluxCreate(){
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }

}