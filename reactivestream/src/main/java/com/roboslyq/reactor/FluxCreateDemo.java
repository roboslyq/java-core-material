/**
 * Copyright (C), 2015-2019
 * FileName: FluxCreateDemo
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

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

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
public class FluxCreateDemo {

    //1、创建Flux============================================================================

    //    just()：可以指定序列中包含的全部元素。创建出来的 Flux 序列在发布这些元素之后会自动结束。
    //    fromArray()，fromIterable()和 fromStream()：可以从一个数组、Iterable 对象或 Stream 对象中创建 Flux 对象。
    //    empty()：创建一个不包含任何元素，只发布结束消息的序列。
    //    error(Throwable error)：创建一个只包含错误消息的序列。
    //    never()：创建一个不包含任何消息通知的序列。
    //    range(int start, int count)：创建包含从 start 起始的 count 个数量的 Integer 对象的序列。
    //    interval(Duration period)和 interval(Duration delay, Duration period)：
    //                  创建一个包含了从 0 开始递增的 Long 对象的序列。其中包含的元素按照指定的间隔来发布。
    //                  除了间隔时间之外，还可以指定起始元素发布之前的延迟时间。
    //    intervalMillis(long period)和 intervalMillis(long delay, long period)：与 interval()方法的作用相同，只不过该方法通过毫秒数来指定时间间隔和延迟时间。

    @Test
    public void createFlux() throws InterruptedException {
        /*
         * Flux.just()方法创建
         */
        Flux<String> flux = Flux.just("a","b","c","d");
        flux.subscribe(System.out::println);

        /*
         * generator创建：generate(Consumer<SynchronousSink<T>> generator)，即传入一个SynchronousSink，通过调用这个Sink来实现元素发送。
         * 1、序列的产生是通过调用所提供的 SynchronousSink 对象的 next()，complete()和 error(Throwable)方法来完成的
         * 2、next()方法只能最多被调用一次
         */
        Flux.generate(sink->{
            sink.next("hello world");
            sink.complete();
        }).subscribe(System.out::println);

        /*
         * generator创建：generate(Callable<S> stateSupplier, BiFunction<S, SynchronousSink<T>, S> generator)
         * 1、相比于上一个方法，此方法传入了一个Callable，用来生成"状态记录"的对象 ，常见的比如数组。可以通过数据来记录生成的元素
         */
        Flux.generate(ArrayList::new ,(state,sink)->{
            //将要发送的元素
            String content = "hello world " + state.size();
            //发送元素
            sink.next(content);
            // 假设要求是发送了10个元素就结束发送
            if(state.size() == 10){
                sink.complete();
            }
            //使用状态记录，保持已经发送过的元素
            state.add(content);

            //返回状态记录器(此处必须返回，供下一次元素发送使用)
            return state;
        }).subscribe(System.out::println);


        /*
         * 使用create 相比generate更加简洁的创建Flux。同时支持多个元素发布
         */
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);


        /*
         * 从Publisher中创建
         */
        Flux<String> flux1 = Flux.from(flux);
        flux1.subscribe(System.out::println);

        /*
         *从数组中创建
         */
        String[] strArray = {"a","b","c","d"};
        Flux<String> flux2 = Flux.fromArray(strArray);
        flux2.subscribe(System.out::println);
        /*
         *从Iterable创建，常见的是List
         */
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        Flux<String> flux3 = Flux.fromIterable(list1);
        flux3.subscribe(System.out::println);

        /*
         * 从Stream流中创建
         */
        Stream<String> stream1 = list1.stream();
        Flux<String> flux4 = Flux.fromStream(stream1);
        flux4.subscribe(System.out::println);

        /*
         * 使用range创建: 创建递增序列
         */

        Flux.range(1,10).subscribe(System.out::println);
        /*
         * 创建Long序列流，时间间隔是Duration
         */
//        Flux.interval(Duration.ofSeconds(1)).subscribe(System.out::println);


        /*
         * 创建Long序列流，延迟10开始发送元素，时间间隔是Duration.ofSeconds(1)
         */
        Flux.interval(Duration.ofSeconds(10),Duration.ofSeconds(1)).subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(20);

    }
}