/**
 * Copyright (C), 2015-2021
 * FileName: DemoDAO
 * Author:   luo.yongqian
 * Date:     2021/12/20 23:08
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2021/12/20 23:08      1.0.0               创建
 */
package com.roboslyq.easyexcel;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2021/12/20
 * @since 1.0.0
 */

import java.util.List;

/**
 * 假设这个是你的DAO存储。当然还要这个类让spring管理，当然你不用需要存储，也不需要这个类。
 **/
public class DemoDAO {
    public void save(List<DemoData> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
        list.forEach(System.out::println);
    }
}