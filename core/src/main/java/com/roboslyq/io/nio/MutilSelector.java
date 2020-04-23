/**
 * Copyright (C), 2015-2020
 * FileName: MutilSelector
 * Author:   luo.yongqian
 * Date:     2020/4/10 22:58
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/4/10 22:58      1.0.0               创建
 */
package com.roboslyq.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/4/10
 * @since 1.0.0
 */
public class MutilSelector {
    public static  void main(String[] args) throws IOException {
        Executor executor = Executors.newFixedThreadPool(3);
        Selector selector = Selector.open();
        int initPort =9000;
        for (int i = 0; i < 3; i++) {
           int  port = initPort + i;
            InetSocketAddress address1 = new InetSocketAddress(port);
            ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
            serverSocketChannel.bind(address1);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }

        executor.execute(()->{
            while (true){
                try {
                    int count = selector.select();
                    if(count > 0){
                        Set<SelectionKey> keys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator  = keys.iterator();
                        while (iterator.hasNext()){
                            SelectionKey key = iterator.next();

                            if(key.isAcceptable()){
                                System.out.println("连接成功");
                                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                                SocketChannel socketChannel = channel.accept();
                                socketChannel.configureBlocking(false);
                                socketChannel.register(selector,SelectionKey.OP_READ);
                            }else if(key.isReadable()){
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                socketChannel.read(byteBuffer);
                                byteBuffer.flip();
                                System.out.println(new String(byteBuffer.array()));
                            }
                        }
                        keys.clear();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}