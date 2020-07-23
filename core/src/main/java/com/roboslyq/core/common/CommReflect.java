/**
 * Copyright (C), 2015-2020
 * FileName: CommReflect
 * Author:   luo.yongqian
 * Date:     2020/6/30 9:20
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/6/30 9:20      1.0.0               创建
 */
package com.roboslyq.core.common;

/**
 *
 * 〈通用代理类，未实现任何接口〉
 * @author roboslyq
 * @date 2020/6/30
 * @since 1.0.0
 */
public class CommReflect {
    public void doBefore(){
        System.out.println("call before");
    }
    public void doAfter(){
        System.out.println("call after");
    }
}