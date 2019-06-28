/**
 * Copyright (C), 2015-2019
 * FileName: FlowableDemo
 * Author:   luo.yongqian
 * Date:     2019/6/28 9:23
 * Description: Flowable
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/28 9:23      1.0.0               创建
 */
package com.roboslyq.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * 〈Flowable〉带背压的
 *
 * @author luo.yongqian
 * @create 2019/6/28
 * @since 1.0.0
 */
public class FlowableDemo {
    public static void main(String[] args) {
        Flowable.create(emitter -> {
                    int i = 0;
                    for (; ; ) {
                        i++;
                        if (i > 10) {
                            break;
                        }
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                }
                , BackpressureStrategy.ERROR)
                .subscribe(System.out::println);
    }

}