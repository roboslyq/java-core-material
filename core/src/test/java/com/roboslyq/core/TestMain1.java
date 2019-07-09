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

import com.alibaba.fastjson.JSONArray;

import java.util.Collections;

/**
 *
 * 〈通用测试类使用〉
 * 负数使用补码表示，
 * 补码 = 反码 + 1;
 * @author luo.yongqian
 * @create 2019/4/19
 * @since 1.0.0
 */
public class TestMain1 {
    public static void main(String[] args) {
//
//        final int start = Integer.MAX_VALUE - 100;
//        final int end = Integer.MAX_VALUE;
//        int count = 0;
//        for (int i = start; i <= end; i++)
//            count++;
//        System.out.println("count-->" + count);
//        Integer temp = Integer.MAX_VALUE;
//        Integer temp2 = Integer.MAX_VALUE + 1;
//        System.out.println(Integer.MAX_VALUE + 1);
        //正整数1，2，3
        Integer  temp1 = 0b00000000_00000000_00000000_00000001;
        Integer  temp2 = 0b00000000_00000000_00000000_00000010;
        Integer  temp3 = 0b00000000_00000000_00000000_00000011;
        System.out.println(temp1);
        System.out.println(temp2);
        System.out.println(temp3);
        //正整数1，2，3
        Integer  temp4 = 0b10000000_00000000_00000000_00000001;
        Integer  temp4_1 = 0b10000000_00000000_00000000_00000000;
        Integer  temp5 = 0b10000000_11111111_00000000_00000010;
        Integer  temp6 = 0b11111111_11111111_11111111_11111011;
        Integer temp7 = -5;
        System.out.println(Integer.MIN_VALUE);
        System.out.println(temp4);
        System.out.println(temp4_1);
        System.out.println(temp5);
        System.out.println(temp6);
//        long estimate = 10;
//        System.out.println(estimate >>>= 1);
//        System.out.println(1 << 30);
//        System.out.println(1 << 6 );
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add(1);
//        jsonArray.add(2);
//        jsonArray.add(3);
//        jsonArray.add(4);
//
//        JSONArray jsonArray1 = new JSONArray(jsonArray);
//        Collections.reverse(jsonArray1);
//
//        System.out.println(jsonArray);

//        System.out.println(jsonArray1);
    }


}