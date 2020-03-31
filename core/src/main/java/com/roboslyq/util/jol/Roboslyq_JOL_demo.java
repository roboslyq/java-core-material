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
import static java.lang.System.setOut;

/**
 *
 * 〈运行时添加JVM参数：
 *      -Djdk.attach.allowAttachSelf
 *   否则会报如下异常：
 *    WARNING: Unable to get Instrumentation. Dynamic Attach failed. You may add this JAR as -javaagent manually, or supply -Djdk.attach.allowAttachSelf
 * 〉
 * @author luo.yongqian
 * @date 2020/3/26
 * @since 1.0.0
 */
public class Roboslyq_JOL_demo {

    public static void main(String[] args) throws Exception {

        Object o = new Object();
        A a = new A();
        // 查看对象实例的内存布局
        out.println(ClassLayout.parseInstance(o).toPrintable());
        out.println(ClassLayout.parseInstance(a).toPrintable());

        // 查看类的内存布局
        out.println(ClassLayout.parseClass(A.class).toPrintable());

        // 查看VM的详细信息
        out.println(VM.current().details());

        new Thread(new Runnable() {
            @Override
            public void run() {
                out.println("hello world");;
            }
        }).start();

    }

    public static class A {
        boolean f;
        int a =99;
        String b = "hello world";
        B bc = new B();

        public static void sayHello(){
            out.println("a");
        }
    }


    static class B{
        String util;
        int c = 100;
    }

}