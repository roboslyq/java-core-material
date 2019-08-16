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
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0013_romantoint;

/**
 * @author luo.yongqian
 * @create 2019/7/26
 * https://leetcode-cn.com/problems/roman-to-integer/
 * @since 1.0.0
 */
public class RomanToInt {
    public static void main(String[] args) {
        RomanToInt romanToInt = new RomanToInt();
        romanToInt.romanToInt("MCMXCIV");
    }

    public int romanToInt(String s) {
        //高位除取余然后余数再除，得到高位从低位的算法
        String[] bit1 = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        String[] bit10 = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        String[] bit100 = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        String[] bit1000 = { "", "M", "MM", "MMM" };
        int res = 0;
        for(int j = 3;j>0;j--){
            if(s.startsWith(bit1000[j])){
                s = s.replaceFirst(bit1000[j],"");
                res = j * 1000;
                break;
            }
        }
        for(int j = 9;j>0;j--){
            if(s.startsWith(bit100[j])){
                s = s.replaceFirst(bit100[j],"");
                res = res + j * 100;
            }
        }
        for(int j = 9;j>0;j--){
            if(s.startsWith(bit10[j])){
                s = s.replaceFirst(bit10[j],"");
                res = res + j * 10;
            }
        }
        for(int j = 9;j>0;j--){
            if(s.startsWith(bit1[j])){
                s = s.replaceFirst(bit1[j],"");
                res = res + j;
            }
        }
        return res;
    }


}