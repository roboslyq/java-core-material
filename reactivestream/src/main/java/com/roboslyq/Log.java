/**
 * Copyright (C), 2015-2020
 * FileName: Log
 * Author:   luo.yongqian
 * Date:     2020/3/3 8:36
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/3 8:36      1.0.0               创建
 */
package com.roboslyq;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @date 2020/3/3
 * @since 1.0.0
 */
public class Log {
    /**
     * 打印当前线程信息，以检验是否异步
     */
    public static void print() {
        print("");
    }
    public static void print(String message) {
        System.out.printf("当前线程：%s,%s,%s \n", Thread.currentThread().getId(), Thread.currentThread().getName(),message);
    }

}