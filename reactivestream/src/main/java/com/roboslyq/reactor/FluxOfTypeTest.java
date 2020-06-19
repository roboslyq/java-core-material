/**
 * Copyright (C), 2015-2020
 * FileName: FluxOfTypeTest
 * Author:   luo.yongqian
 * Date:     2020/6/18 9:03
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/6/18 9:03      1.0.0               创建
 */
package com.roboslyq.reactor;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/6/18
 * @since 1.0.0
 */
public class FluxOfTypeTest {
    public static void main(String[] args) {
        Flux.just(1,2,3)
                .ofType(Integer.TYPE)
                .subscribe(System.out::println);

    }

}