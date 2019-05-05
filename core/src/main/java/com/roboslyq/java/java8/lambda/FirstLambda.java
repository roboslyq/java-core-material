/**
 * Copyright (C), 2015-2019
 * FileName: FirstLambda
 * Author:   luo.yongqian
 * Date:     2019/4/28 10:09
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/4/28 10:09      1.0.0               创建
 */
package com.roboslyq.java.java8.lambda;

import java.util.function.BinaryOperator;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/4/28
 * @since 1.0.0
 */
public class FirstLambda {
    public static void main(String[] args) {
        //调用方法实现，传入函数式接口
        String calculateResult= calculateImpl(10, 11, (v1, v2) -> {
            return String.valueOf(v1 + v2);
        });
        System.out.println(calculateResult);

        //没有参数的函数式接口
        Runnable runnable = () ->{
            System.out.println("no arguments function method!!");
        };
        runnable.run();

        //两个参数加返回值
        BinaryOperator<Long> add = (x, y) -> x + y;
        System.out.println(add.apply(1l,2l));
    }

    /**
     * 定义需要使用函数式接口的方法，此方法参数可以根据实际情况指定泛型值。
     * @param val1
     * @param val2
     * @param calculate
     * @return
     */
    private static String calculateImpl(Integer val1, Integer val2, Calculate<Integer, Integer, String> calculate){

        return calculate.operation(val1,val2);
    }
}

/**
 * 定义函数式接口(只有一个抽象方法的接口)
 * @param <V>
 * @param <K>
 * @param <T>
 */
@FunctionalInterface
interface Calculate<V,K,T>{
    public  T operation(V v,K k);
}