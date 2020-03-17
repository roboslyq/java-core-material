/**
 * Copyright (C), 2015-2020
 * FileName: Log
 * Author:   luo.yongqian
 * Date:     2020/3/17 17:54
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/17 17:54      1.0.0               创建
 */
package com.roboslyq.reactor;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @date 2020/3/17
 * @since 1.0.0
 */
public class Log {
    public static void print(Object obj){
        String threadName = Thread.currentThread().getName();
        System.out.println("[ "+ threadName +" ] : " + obj);
    }
}