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
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0012_inttoroman;

/**
 *
 * 〈映射法：每个位的情况共有，使用Map保存结果，然后根据每一位映射〉
 * @author luo.yongqian
 * @create 2019/7/26
 * https://leetcode-cn.com/problems/integer-to-roman/
 * @since 1.0.0
 */
public class IntToRoman1 {
    public static void main(String[] args) {
        IntToRoman1 intToRoman = new IntToRoman1();
//        System.out.println(intToRoman.intToRoman(3));
//        System.out.println(intToRoman.intToRoman(4));
//        System.out.println(intToRoman.intToRoman(9));
//        System.out.println(intToRoman.intToRoman(58));
        System.out.println(intToRoman.intToRoman(2994));
    }


    public String intToRoman(int num) {
        //高位除取余然后余数再除，得到高位从低位的算法
        String[] bit1 = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        String[] bit10 = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        String[] bit100 = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        String[] bit1000 = { "", "M", "MM", "MMM" };
        return bit1000[num / 1000] + bit100[num % 1000 / 100] + bit10[num % 100 / 10] + bit1[num % 10];
    }

}