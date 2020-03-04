/**
 * Copyright (C), 2015-2019
 * FileName: Parent
 * Author:   luo.yongqian
 * Date:     2019/9/6 12:28
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/6 12:28      1.0.0               创建
 */
package com.roboslyq.other.builder;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/9/6
 * @since 1.0.0
 */
public class Parent<T extends Parent> {
    public String id1;

    public String getId1() {
        return id1;
    }

    public T setId1(String id1) {
        this.id1 = id1;
        return (T) this;
    }
}