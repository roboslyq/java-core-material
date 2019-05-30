/**
 * Copyright (C), 2015-2019
 * FileName: PhaserDemoMain
 * Author:   luo.yongqian
 * Date:     2019/5/29 15:52
 * Description: Phaser 引导类
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/29 15:52      1.0.0               创建
 */
package com.roboslyq.concurrent.synchronize;

import java.util.concurrent.Phaser;

/**
 *
 * 〈Phaser 引导类〉
 * @author luo.yongqian
 * @create 2019/5/29
 * @since 1.0.0
 */
public class PhaserDemoMain {
    public static void main(String[] args) {
        //设置每阶的栅栏数量：例如3，表示需要3个线程到达才能通过栅栏
        Phaser phaser = new Phaser(3);
        //循环3次，模拟三个线程到达栅栏处
        for (int i = 0; i < 3; i++) {
            PhaserDemo.PhaserThread threadA = new PhaserDemo.PhaserThread(phaser);
            threadA.start();
        }
    }
}