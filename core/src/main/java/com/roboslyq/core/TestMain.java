/**
 * Copyright (C), 2015-2019
 * FileName: TestMain
 * Author:   luo.yongqian
 * Date:     2019/4/19 14:58
 * Description: 通用测试类使用
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/4/19 14:58      1.0.0               创建
 */
package com.roboslyq.core;

/**
 *
 * 〈通用测试类使用〉
 * @author luo.yongqian
 * @create 2019/4/19
 * @since 1.0.0
 */
public class TestMain {
    public static void main(String[] args) {
        int a = 126;
        int c = 127;
        String b = "hello,world";
        calculate(a,b);
        System.out.println(a);
        System.out.println(b);
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(c));
    }

    public static void calculate(int a ,String b){
        a++;
        b = new String("hello,world");
        System.out.println("calculate b = " + System.identityHashCode(b));
        System.out.println("calculate a = " + System.identityHashCode(a));
    }

}