/**
 * Copyright (C), 2015-2020
 * FileName: CollectorsDemo
 * Author:   luo.yongqian
 * Date:     2020/3/3 15:53
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/3 15:53      1.0.0               创建
 */
package com.roboslyq.java.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @date 2020/3/3
 * @since 1.0.0
 */
public class CollectorsDemo {
    public static void main(String[] args) {
        List<Order> list = new ArrayList<>();

        List<List<Order>> group1 = new ArrayList<>();
        List<List<Order>> group2 = new ArrayList<>();

        list.stream().forEach(order -> {
      
        });

    }

}
class Order{
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}