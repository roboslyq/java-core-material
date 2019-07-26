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
package com.roboslyq.algorithm.leetcode.inttoroman_0012;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/7/26
 * https://leetcode-cn.com/problems/integer-to-roman/
 * @since 1.0.0
 */
public class IntToRoman {
    public static void main(String[] args) {

    }
    public String intToRoman(int num) {
        Map<Integer,String> romanMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String result = "";
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
        romanMap.put(100,"C");
        romanMap.put(500,"D");
        romanMap.put(1000,"M");
        int bit = 10;
        while(num > 0){
            int mode = num % bit;
            result
        }

    }
}