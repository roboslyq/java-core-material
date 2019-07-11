/**
 * Copyright (C), 2015-2019
 * FileName: BasicStreamDemo
 * Author:   luo.yongqian
 * Date:     2019/4/28 11:00
 * Description: 第一个Sream示例
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/4/28 11:00      1.0.0               创建
 */
package com.roboslyq.java.java8.stream;

import java.util.stream.Collectors;

/**
 * Copyright (C), 2015-2019
 * FileName: LengthOfLongestSubstring
 * Author:   luo.yongqian
 * Date:     2019/4/19 14:58
 * Description: 通用测试类使用
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/4/19 14:58      1.0.0               创建
 */

import java.util.*;

/**
 *
 * 〈通用测试类使用〉
 * @author luo.yongqian
 * @create 2019/4/19
 * @since 1.0.0
 */
public class BasicStreamDemo3 {
    private static String value ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmopqrstuvwxyz0123456789";
    public static void main(String[] args) {
        System.out.println("start generic... ");
        LinkedList<String> linkedList = BasicStreamDemo3.genericStr(50000000);
        System.out.println("generic complete... ");
        Long startMills = System.currentTimeMillis();
        Optional<Map.Entry<String, Long>> result = linkedList.stream()
                .parallel()
                .collect(Collectors.groupingBy(
                        String::toString
                        ,Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(
                        Map.Entry::getValue));
        System.out.println(result);
        Long timeUsed = System.currentTimeMillis() - startMills;
        System.out.println("used time : " + timeUsed);
    }

    private static LinkedList genericStr(int arraySize){
        LinkedList<String> linkedList = new LinkedList<String>();
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0;i< arraySize;i++){
            int v1 = random.nextInt(61);
            char c1 = value.charAt(v1);
            int v2 = random.nextInt(61);
            char c2 = value.charAt(v1);
            int v3 = random.nextInt(61);
            char c3 = value.charAt(v1);
            linkedList.add(String.valueOf(c1) + String.valueOf(c2) + String.valueOf(c3));
        }
        return  linkedList;
    }
}

