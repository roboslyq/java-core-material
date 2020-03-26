/**
 * Copyright (C), 2015-2020
 * FileName: JOLSample_01_Basic
 * Author:   luo.yongqian
 * Date:     2020/3/26 22:08
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/26 22:08      1.0.0               创建
 */
package com.roboslyq.util.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import static java.lang.System.out;
/**
 *
 * 〈〉
 * @author luo.yongqian
 * @date 2020/3/26
 * @since 1.0.0
 */
public class JOLSample_01_Basic {
    /*
     * This sample showcases the basic field layout.
     * You can see a few notable things here:
     *   a) how much the object header consumes;
     *   b) how fields are laid out;
     *   c) how the external alignment beefs up the object size
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    public static class A {
        boolean f;
        int a =99;
        String b = "hello world";
    }

}