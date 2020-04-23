/**
 * Copyright (C), 2015-2020
 * FileName: NioRedisTest
 * Author:   luo.yongqian
 * Date:     2020/4/23 14:15
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/4/23 14:15      1.0.0               创建
 */
package com.roboslyq.io.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 〈〉
 *
 * @author roboslyq
 * @date 2020/4/23
 * @since 1.0.0
 */
public class NioRedisTest {

    private static volatile ArrayList<SocketChannel> socketList = new ArrayList<>();
    private static ExecutorService acceptExecutor = Executors.newFixedThreadPool(1);
    private static ExecutorService readExecutor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6380);
        serverChannel.bind(socketAddress);
        serverChannel.configureBlocking(false);
        //启动服务
        accept(serverChannel);
        //处理IO
        for (;;) {
            for (SocketChannel socketChannel : socketList) {
                read(socketChannel);
            }
            TimeUnit.MICROSECONDS.sleep(50);
        }
    }

    /**
     * 接收连接
     * @param serverChannel
     */
    public static void accept(ServerSocketChannel serverChannel){
        acceptExecutor.submit(()->{
            System.out.println("服务启动成功,监听端口:6380");
            for (;;){
                SocketChannel clientsocket = serverChannel.accept();
                if (clientsocket != null) {
                    clientsocket.configureBlocking(false);
                    socketList.add(clientsocket);
                    System.out.println("收到新客户端： "+ clientsocket.getRemoteAddress() + "连接; 当前已连接客户端：" + socketList.size());
                }
                TimeUnit.MICROSECONDS.sleep(50);
            }
        });
    }
    /**
     * I/O处理S
     * @param clientChannel
     */
    public static void read(SocketChannel clientChannel){
        readExecutor.submit(()->{
            for (;;){
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = clientChannel.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    byte[] bs = new byte[read];
                    byteBuffer.get(bs);
                    String content = new String(bs);
                    System.out.println("接收到请求内容:" + content);
                    byteBuffer.flip();
                }
            }
        });
    }
}