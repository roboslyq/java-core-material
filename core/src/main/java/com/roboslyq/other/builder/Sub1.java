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
package com.roboslyq.other.builder;

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



}