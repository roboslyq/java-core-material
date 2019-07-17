/**
 * Copyright (C), 2015-2019
 * FileName: Elvis
 * Author:   luo.yongqian
 * Date:     2019/7/15 11:21
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/7/15 11:21      1.0.0               创建
 */
package com.roboslyq;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/7/15
 * @since 1.0.0
 */
import java.util.Calendar;

public class Elvis {

    public static final Elvis INSTANCE = new Elvis();
    private static final int CURRENT_YEAR =
            Calendar.getInstance().get(Calendar.YEAR);
    private final int beltSize;



    private Elvis() {
        beltSize = CURRENT_YEAR - 1930;
    }

    public int beltSize() {
        return beltSize;
    }

    public static void main(String[] args) {
        System.out.println("Elvis wears size " +
                INSTANCE.beltSize() + " belt.");
    }
}