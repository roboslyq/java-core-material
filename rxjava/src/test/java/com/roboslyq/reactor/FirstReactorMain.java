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
        GenerateFluxDemo.simpleFluxByStaticMethod();
        GenerateFluxDemo.fluxGenerate();
        GenerateFluxDemo.fluxCreate();
    }
}