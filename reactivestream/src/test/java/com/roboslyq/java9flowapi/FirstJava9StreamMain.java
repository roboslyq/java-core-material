/**
 * Copyright (C), 2015-2019
 * FileName: FirstJava9StreamMain
 * Author:   luo.yongqian
 * Date:     2019/5/6 15:01
 * Description: java9Stream API使用
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/6 15:01      1.0.0               创建
 */
package com.roboslyq.java9flowapi;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * 〈java9Stream API使用〉
 * @author luo.yongqian
 * @create 2019/5/6
 * @since 1.0.0
 */
public class FirstJava9StreamMain {
    public static void main(String[] args) {
        Stream.of("a","b","c","","e","f").takeWhile(s->!s.isEmpty())
                .forEach(System.out::print);

        Stream.of("a","b","c","","e","f").dropWhile(s-> !s.isEmpty())
                .forEach(System.out::print);

        IntStream.iterate(3, x -> x < 10, x -> x+ 3).forEach(System.out::println);

    }

}