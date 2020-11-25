/**
 * Copyright (C), 2015-2020
 * FileName: CharactorTest
 * Author:   luo.yongqian
 * Date:     2020/8/7 8:48
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/8/7 8:48      1.0.0               创建
 */
package com.roboslyq.temp.charactor;

import java.io.UnsupportedEncodingException;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/8/7
 * @since 1.0.0
 */
public class CharactorTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "中";
        print(str.getBytes());
        print(str.getBytes("ISO-8859-1"));
        print(str.getBytes("GBK"));
        print(str.getBytes("GB2312"));
        print(str.getBytes("UTF-8"));

    }

    private static void print(byte[] b){
        for (byte b1 : b){
            System.out.print(b1 + "|");
        }
        System.out.println();
    }

}