/**
 * Copyright (C), 2015-2019
 * FileName: ChainsMain
 * Author:   luo.yongqian
 * Date:     2019/6/25 13:28
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/25 13:28      1.0.0               创建
 */
package com.roboslyq.chains;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public class ChainsMain {
    public static void main(String[] args) {
        Nodes.create(()->( 10)).provide(()-> System.out.println());
    }
}