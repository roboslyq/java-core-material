package com.roboslyq.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 * 创建Flux
 */
public class GenerateFluxDemo {
    /**
     * 通过 Flux 类的静态方法创建 Flux 序列
     */
    public static void simpleFluxByStaticMethod(){
        Flux.generate(sink -> {
            sink.next("hello world1");
//            sink.complete();
        }).subscribe(System.out :: println);
//        Flux.just("Hello","World").subscribe(System.out::println);
//        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
//        Flux.empty().subscribe(System.out::println);
//        Flux.range(1, 10).subscribe(System.out::println);
//        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
    }
    /**
     * 使用 generate()方法生成 Flux 序列
     */
    public static void fluxGenerate(){
        //生产单个元素即结束
        Flux.generate(sink -> {
            sink.next("hello world1");
            sink.complete();
        }).subscribe(System.out :: println);

        //生产多个元素
        Random random = new Random();
        Count count = new Count();
        Flux.generate(sink -> {
            sink.next(random.nextInt());
            count.increase();
            if (count.count == 99) {
                sink.complete();
            }
        }).subscribe(System.out::println);


        Flux.generate(Count::new,(c, sink) -> {
            c.increase();
            sink.next(random.nextInt());
            count.increase();
            if (c.count == 99) {
                sink.complete();
            }
            return count;
        }).subscribe(System.out::println);

        //指定
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
