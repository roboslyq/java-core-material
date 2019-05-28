package com.roboslyq.concurrent.synchronize;

import java.util.concurrent.Phaser;

/**
 * 多阶栅栏
 */
public class PhaserDemo {
    public static void main(String[] args) {
        //设置每阶的栅栏数量：例如3，表示需要3个线程到达才能通过栅栏
        Phaser phaser = new Phaser(3);
        //循环3次，模拟三个线程到达栅栏处
        for (int i = 0; i < 3; i++) {
            ThreadA threadA = new ThreadA(phaser);
            threadA.start();
        }
    }

    /**
     * 线程模拟
     */
    static class ThreadA extends Thread {
        /**
         * 信号量
         */
        private Phaser phaser;
        public ThreadA(Phaser phaser) {
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
