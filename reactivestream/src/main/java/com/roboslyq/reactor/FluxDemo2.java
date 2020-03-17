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

import reactor.core.publisher.Flux;

import java.util.Random;

/**
 * 〈〉
 *
 * @author luo.yongqian
 * @date 2020/3/5
 * @since 1.0.0
 */
public class FluxDemo2 {
    public static void main(String[] args) {
        Random random = new Random();
        Count count = new Count();
        Flux.generate(sink -> {
            sink.next(random.nextInt());
            count.increase();
            if (count.count == 99) {
                sink.complete();
            }
        }).subscribe(System.out::println);
    }
}


class Count{
    public int count;
    public void  increase(){
      count++;
    }
}