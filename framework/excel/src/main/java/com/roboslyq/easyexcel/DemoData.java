/**
 * Copyright (C), 2015-2021
 * FileName: DemoData
 * Author:   luo.yongqian
 * Date:     2021/12/20 23:03
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2021/12/20 23:03      1.0.0               创建
 */
package com.roboslyq.easyexcel;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2021/12/20
 * @since 1.0.0
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DemoData {
    private String string;
    private Date date;
    private Double doubleData;

}