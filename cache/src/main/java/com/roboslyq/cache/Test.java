/**
 * Copyright (C), 2015-2020
 * FileName: Test
 * Author:   luo.yongqian
 * Date:     2020/4/24 17:45
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/4/24 17:45      1.0.0               创建
 */
package com.roboslyq.cache;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/4/24
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        String str2 = "<k>ha>ve modif<ied <<new.&txt</k>";
        String str1 = "";

        str1 = "";
        while(!str2.equals(str1)){
            str1 = str2;
            str2 = str1.replaceAll("(<k>.*)(&)(.*</k>)", "$1&gt;$3");
        }

        str1 = "";
        while(!str2.equals(str1)){
            str1 = str2;
            str2 = str1.replaceAll("(<k>.*)(<)(.*</k>)", "$1&lt;$3");
        }
        str1 = "";
        while(!str2.equals(str1)){
            str1 = str2;
            str2 = str1.replaceAll("(<k>.*)(>)(.*</k>)", "$1&gt;$3");
        }

        System.out.println(str2);

    }
}