/**
 * Copyright (C), 2015-2019
 * FileName: OperatorInterface
 * Author:   luo.yongqian
 * Date:     2019/6/25 10:30
 * Description: 具体操作接口
 * History:
 * <author>          <time>          <version>          <desc>
 * luo.yongqian         2019/6/2510:30      1.0.0               创建
 */
package com.roboslyq.chains;

import java.util.function.Consumer;

/**
 *
 * 〈具体操作接口〉
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public interface Provider<T> {
  void deal(Subscriber<T> subscriber);
}