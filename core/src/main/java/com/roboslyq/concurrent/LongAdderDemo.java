/**
 * Copyright (C), 2015-2019
 * FileName: LongAdderDemo
 * Author:   luo.yongqian
 * Date:     2019/5/22 16:26
 * Description: LongAdder 示例
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/22 16:26      1.0.0               创建
 */
package com.roboslyq.concurrent;

import java.util.concurrent.atomic.LongAdder;

/**
 *
 * 〈LongAdder 示例〉
 * @author luo.yongqian
 * @create 2019/5/22
 * @since 1.0.0
 */
public class LongAdderDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        long r = Double.doubleToRawLongBits(2);
        System.out.println(r);
    }
}