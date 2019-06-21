/**
 * Copyright (C), 2015-2019
 * FileName: FluxDemoMain
 * Author:   luo.yongqian
 * Date:     2019/5/22 14:38
 * Description: Flux Demo main
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/22 14:38      1.0.0               创建
 */
package com.roboslyq.reactor;

import com.roboslyq.reactor.FluxDemo;

/**
 *
 * 〈Flux Demo main〉
 * @author luo.yongqian
 * @create 2019/5/22
 * @since 1.0.0
 */
public class FluxDemoMain {
    public static void main(String[] args) {
//        FluxDemo.synchronizedDemo();
        FluxDemo.asynchronizedDemo();
//        FluxDemo.subscribeLambda();
//        FluxDemo.subscribeLambd1();
//        FluxDemo.subscribeLambd2();
//        FluxDemo.subscribeLambd3();
    }
}