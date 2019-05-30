package com.roboslyq.concurrent.synchronize;

import java.util.concurrent.Phaser;

/**
 * 多阶栅栏
 */
public class PhaserDemo {
    /**
     * 线程模拟
     */
    static class PhaserThread extends Thread {
        /**
         * 信号量
         */
        private Phaser phaser;

        PhaserThread(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            //当前线程完成任务模拟
            System.out.println(Thread.currentThread().getName() + " A begin " + System.currentTimeMillis());
            //当前线程等待其它线程完成任务到达栅栏处
            phaser.arriveAndAwaitAdvance();
            //通过栅栏处理继续处理
            System.out.println(Thread.currentThread().getName() + " A end " + System.currentTimeMillis());
        }
    }
}
