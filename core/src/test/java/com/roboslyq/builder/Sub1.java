/**
 * Copyright (C), 2015-2019
 * FileName: Sub1
 * Author:   luo.yongqian
 * Date:     2019/9/6 12:29
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/6 12:29      1.0.0               创建
 */
package com.roboslyq.builder;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/9/6
 * @since 1.0.0
 */
public class Sub1 extends Parent<Sub1> {
    public String id2;

    public String getId2() {
        return id2;
    }

    public Sub1 setId2(String id2) {
        this.id2 = id2;
        return  this;
    }

    public static void main(String[] args) {
        Sub1 sub1 = new Sub1();
        Sub1 sub12 = sub1.setId1("1")
                .setId2("2");
    }

}