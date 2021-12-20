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
package com.roboslyq.easypoi;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.*;

import java.util.Date;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2021/12/20
 * @since 1.0.0
 */
@Data
@ToString
public class DemoData {
    @Excel(name = "债务人ID" ,fixedIndex = 0)
    private String string;
    @Excel(name = "债务人名称",fixedIndex = 1)
    private Date date;
    @Excel(name = "债务人编码",fixedIndex = 2)
    private Double doubleData;

}