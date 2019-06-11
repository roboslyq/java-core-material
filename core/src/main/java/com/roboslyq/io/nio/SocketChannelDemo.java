package com.roboslyq.io.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketChannelDemo {
    public void clientService(int port) throws InterruptedException {
        // 接下来模拟3个Client并发访问服务器
        int poolsize = 3;
        ExecutorService pool = Executors.newFixedThreadPool(poolsize);
        Collection<Callable<Integer>> tasks = new ArrayList<>(10);
        final String clientname = "clientThread";
        for (int i = 0; i < poolsize; i++) {
            final int n = i;
            // 若每一个Client都保持使用BIO方式发送数据到Server，并读取数据。
            tasks.add(() -> {
                Socket socket = new Socket("127.0.0.1", port);
                final InputStream input = socket.getInputStream();
                final OutputStream out = socket.getOutputStream();
                final String clientname_n = clientname + "_" + n;
                /**
                 * 1、在Callable接口中的call()方法，加入两个线程：1个读线程和1个写线程
                 * 2、当Callable.call()被 ExecutorService触发时，会分别启动1个读线程和1个写线程。
                 * 3、此处读写线程设定返回值为Integer类型，还可以定义为其它类型或者没有返回值直接使用Runnable比较好
                 */
                new Thread(clientname_n + "_read") {
                    @Override
                    public void run() {
                        byte[] bs = new byte[1024];
                        while (true) {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            int len = 0;
                            try {
                                while ((len = input.read(bs)) != -1) {
                                    System.out.println("当前客户端线程: " + Thread.currentThread() .getName()+ "收到来自于服务器的响应： "
                                            + new String(bs, 0, len));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                // BIO写数据线程
                new Thread(clientname_n + "_write") {
                    @Override
                    public void run() {
                        int a = 0;
                        do {
                            try {
                                //模拟业务调用，1s调用一次
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            String str = "服务器你好，我是线程： " + Thread.currentThread().getName() + "现在向你发送第[ " + a + " ]条信息";
                            try {
                                out.write(str.getBytes());
                                a++;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } while (true);
                    }
                }.start();
                return 0;
            });
        }
        //提交并启动任务
        pool.invokeAll(tasks);
    }
}
