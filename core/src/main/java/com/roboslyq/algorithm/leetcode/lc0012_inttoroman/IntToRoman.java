/**
 * Copyright (C), 2015-2019
 * FileName: IntToRoman
 * Author:   luo.yongqian
 * Date:     2019/7/26 13:10
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/7/26 13:10      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0012_inttoroman;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 〈映射法：每个位的情况共有，使用Map保存结果，然后根据每一位映射〉
 * @author luo.yongqian
 * @create 2019/7/26
 * https://leetcode-cn.com/problems/integer-to-roman/
 * @since 1.0.0
 */
public class IntToRoman {
    public static void main(String[] args) {
        IntToRoman intToRoman = new IntToRoman();
//        System.out.println(intToRoman.intToRoman(3));
//        System.out.println(intToRoman.intToRoman(4));
//        System.out.println(intToRoman.intToRoman(9));
//        System.out.println(intToRoman.intToRoman(58));
        System.out.println(intToRoman.intToRoman(1994));
    }

    public String intToRoman(int num) {
         Map<Integer,String> romanMap = new HashMap<>();
            romanMap.put(0,"");
            romanMap.put(1,"I");
            romanMap.put(2,"II");
            romanMap.put(3,"III");
            romanMap.put(4,"IV");
            romanMap.put(5,"V");
            romanMap.put(6,"VI");
            romanMap.put(7,"VII");
            romanMap.put(8,"VIII");
            romanMap.put(9,"IX");
            romanMap.put(10,"X");
            romanMap.put(20,"XX");
            romanMap.put(30,"XXX");
            romanMap.put(40,"XL");
            romanMap.put(50,"L");
            romanMap.put(60,"LX");
            romanMap.put(70,"LXX");
            romanMap.put(80,"LXXX");
            romanMap.put(90,"XC");
            romanMap.put(100,"C");
            romanMap.put(200,"CC");
            romanMap.put(300,"CCC");
            romanMap.put(400,"CD");
            romanMap.put(500,"D");
            romanMap.put(600,"DC");
            romanMap.put(700,"DCC");
            romanMap.put(800,"DCCC");
            romanMap.put(900,"CM");
            romanMap.put(1000,"M");
            romanMap.put(2000,"MM");
            romanMap.put(3000,"MMM");
        String res = "";

        int bit = 10;
        int mutiple = 1;
        while(num * 10/bit > 0){
            int mode = num % 10;
            res = String.format("%s%s", romanMap.get(mode * mutiple), res);
            num = num /10;
            mutiple *= 10;
        }
        return  res;
    }

}