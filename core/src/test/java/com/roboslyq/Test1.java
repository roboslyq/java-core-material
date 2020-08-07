/**
 * Copyright (C), 2015-2020
 * FileName: Test1
 * Author:   luo.yongqian
 * Date:     2020/2/13 22:17
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/2/13 22:17      1.0.0               创建
 */
package com.roboslyq;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @date 2020/2/13
 * @since 1.0.0
 */
public class Test1 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "roboslyq中国人";
        print(str.getBytes());
        print(str.getBytes("ISO-8859-1"));
        print(str.getBytes("UTF-8"));
        print(str.getBytes("GBK"));
        System.out.println(new String(str.getBytes()));
        System.out.println(new String(str.getBytes("ISO-8859-1")));
        System.out.println(new String(str.getBytes("UTF-8")));
        System.out.println(new String(str.getBytes("GBK")));
//        int a = Integer.MIN_VALUE;
//        while (!calc(a)){
//            a++;
//        }

    }
    private static void print(byte[] b){
        for (byte b1 : b){
            System.out.print(b1+"|");
        }
        System.out.println();
    }
    public static boolean calc(int a ){
       int b = a - 9;
       int c = 12 - a;
       int d = 2 - b;
       if(c - d == 14 || a >= Integer.MAX_VALUE){
           System.out.println(a);
           System.out.println(b);
           System.out.println(c);
           System.out.println(d);
           System.out.println("a - b: " + (a -b));
           System.out.println("a + c: " + (a +c));
           System.out.println("c - d: " + (c -d));
           System.out.println("b + d: " + (b + d));
           return true;
       }else {
           return false;
       }
    }
}