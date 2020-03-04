/**
 * Copyright (C), 2015-2020
 * FileName: BuilderDemoTest
 * Author:   luo.yongqian
 * Date:     2020/3/4 9:10
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/4 9:10      1.0.0               创建
 */
package com.roboslyq.other.builder;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @date 2020/3/4
 * @since 1.0.0
 */
public class BuilderDemoTest {
    public static void main(String[] args) {
        Sub1 sub1 = new Sub1();
        Sub1 sub12 = sub1.setId1("1")
                .setId2("2");
        System.out.println(sub12.id1);
    }
}