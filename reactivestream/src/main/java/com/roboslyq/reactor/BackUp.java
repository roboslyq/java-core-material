/**
 * Copyright (C), 2015-2020
 * FileName: BackUp
 * Author:   luo.yongqian
 * Date:     2020/3/5 17:24
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/5 17:24      1.0.0               创建
 */
package com.roboslyq.reactor;

import reactor.core.publisher.Flux;

/**
 *
 * 〈背压测试〉
 * @author luo.yongqian
 * @date 2020/3/5
 * @since 1.0.0
 */
public class BackUp {
    public static void main(String[] args) {
        Flux.just("A", "B", "C")
                .any(item -> {
                    return item.equals("A");
                }).subscribe(System.out::println);

    }
}