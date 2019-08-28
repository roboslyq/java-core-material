/**
 * Copyright (C), 2015-2019
 * FileName: CountAndSay
 * Author:   luo.yongqian
 * Date:     2019/8/28 12:51
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/8/28 12:51      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0038_countandsay;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/8/28
 * @since 1.0.0
 */
public class CountAndSay {
    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(1));
        System.out.println(countAndSay.countAndSay(2));
        System.out.println(countAndSay.countAndSay(3));
        System.out.println(countAndSay.countAndSay(4));
        System.out.println(countAndSay.countAndSay(5));
//        System.out.println(countAndSay.next("21"));
        System.out.println(countAndSay.countAndSay(6));
    }
    public String countAndSay(int n) {
        String res = "";
        for (int i = 0;i<n;i++){
           if(i == 0){
               res = "1";
           }else{
               res = next(res);
           }
       }
        return res;
    }

    public String next(String current){
        StringBuilder bufferRes = new StringBuilder();
        for(int j = 0;j<current.length();){
            int count = 0;
            char currentChar = current.charAt(j);
            while(j<current.length() && current.charAt(j) == currentChar ){
                count++;
                j++;

            }
            bufferRes.append(count).append(currentChar);
        }
        return bufferRes.toString();
    }

}