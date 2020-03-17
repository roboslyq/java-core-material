/**
 * Copyright (C), 2015-2020
 * FileName: MonoTest
 * Author:   luo.yongqian
 * Date:     2020/3/13 15:44
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/13 15:44      1.0.0               创建
 */
package com.roboslyq.reactor;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @date 2020/3/13
 * @since 1.0.0
 */
public class MonoTest {
    public static void main(String[] args) {
        /*
         *just:POJO作为Mono的数据源
         */
        Mono.just("hello mono").subscribe(System.out::println);
        //将一个集合作为Mono元素
        Mono.just(Arrays.asList(1,2,3,4,5)).subscribe(list->{
            list.forEach(System.out::println);
        });
        System.out.println("------------------------");
        //空Mono
        Mono.empty().subscribe(System.out::println);
        Mono.empty().switchIfEmpty(Mono.just("empty test"))
                    .subscribe(System.out::println);
        System.out.println("------------------------");

        //
        Mono.just("1,2,3").defer(()-> Mono.just("1")).subscribe(System.out::println);
        Mono.defer(()-> Mono.just("1")).subscribe(System.out::println);
        System.out.println("------------------------");

    }

}