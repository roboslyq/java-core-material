/**
 * Copyright (C), 2015-2019
 * FileName: Publishers
 * Author:   luo.yongqian
 * Date:     2019/6/25 14:08
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/25 14:08      1.0.0               创建
 */
package com.roboslyq.chains;

/**
 *
 * 〈Publisher的工具类〉
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public class Publishers<T> {

    /**
     * 源头Node需要手动创建
     * @return
     */
    public static <T> AbstractPublisher<T> create(OnSubscribeProcessor<T> processor){
        return new CreatePublisher<T>(processor);
    }

}