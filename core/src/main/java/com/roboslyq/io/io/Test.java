/**
 * Copyright (C), 2015-2020
 * FileName: Test
 * Author:   luo.yongqian
 * Date:     2020/7/24 11:44
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/7/24 11:44      1.0.0               创建
 */
package com.roboslyq.io.io;

import java.io.File;
import java.io.IOException;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/7/24
 * @since 1.0.0
 */
public class Test {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\A1\\B1\\a1s.txt");
        file.mkdir();
        file.createNewFile();
    }
}