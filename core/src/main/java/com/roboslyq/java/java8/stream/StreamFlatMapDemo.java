/**
 * Copyright (C), 2015-2019
 * FileName: StreamFlatMapDemo
 * Author:   luo.yongqian
 * Date:     2019/5/20 9:59
 * Description: FlatMap原理测试
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/20 9:59      1.0.0               创建
 */
package com.roboslyq.java.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * 〈FlatMap原理测试〉
 * @author luo.yongqian
 * @create 2019/5/20
 * @since 1.0.0
 */
public class StreamFlatMapDemo {
    public static void main(String[] args) {
        String[] list1 = {"Hello", "World;"};
        mapTest(list1);
        flatmapTest(list1);

    }

    /**
     * map方法：将一个流转换成另一个流
     * @param src
     */
    public static void mapTest(String[] src){
      List<String[]> res =  Stream.of(src)
                //将src字的字符串转换成每个单词，并且每个单词组成一个新的流。此流是一个字符串数组
                .map(str ->str.split(""))
                //注意，此处distinct是针对整个字符串数组流，而不是单个字符。
                .distinct()
                .collect(Collectors.toList());
        res.forEach(strArray -> {
            Stream.of(strArray).forEach(System.out::println);
        });
    }

    /**
     * flatmap方法：将多个流合并为一个流
     * @param src
     */
    public static void flatmapTest(String[] src){
        List<String> res =  Stream.of(src)
                //将src字的字符串转换成每个单词，并且每个单词组成一个新的流。此流是一个字符串数组
                .map(str ->str.split(""))
                .flatMap(Arrays::stream)
                //注意，此处distinct是针对单个字符串数组流。
                .distinct()
                .collect(Collectors.toList());
        res.forEach(System.out::println);
    }

}