/**
 * Copyright (C), 2015-2021
 * FileName: DateUtils
 * Author:   luo.yongqian
 * Date:     2021/9/25 11:40
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2021/9/25 11:40      1.0.0               创建
 */
package com.roboslyq.framework.velocity;

import java.util.Date;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2021/9/25
 * @since 1.0.0
 */
public class DateUtils {

    public String getDate(){
       return new Date().toString();
    }

    @Override
    public String toString() {
        return "自定义toString方法";
    }
}