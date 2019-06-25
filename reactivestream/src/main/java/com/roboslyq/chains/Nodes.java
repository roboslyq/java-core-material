/**
 * Copyright (C), 2015-2019
 * FileName: Nodes
 * Author:   luo.yongqian
 * Date:     2019/6/25 14:08
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/25 14:08      1.0.0               创建
 */
package com.roboslyq.chains;

import java.util.function.Supplier;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public class Nodes {

    /**
     * 源头Node需要手动创建
     * @return
     */
    public static AbstractProvider create(OnSubscribeProcessor processor){
        AbstractProvider node = new NodeCreate(processor);
        return node;
    }


}