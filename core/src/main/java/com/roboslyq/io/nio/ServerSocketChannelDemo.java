package com.roboslyq.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ServerSocketChannelDemo {
    //构建Buffer
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    /**
     * 启动一个服务
     * @param port
     */
    public void bootStrapServer(int port) throws IOException {
        /**
         * 第一步：构建Channel并进行相关配置，Channel是一切入口。
         */
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞模式
        serverChannel.configureBlocking(false);
        /**
         * 第二步：从Channel中获取Socket套接字
         */
        ServerSocket serverSocket = serverChannel.socket();
        //套接字绑定IP和端口
        serverSocket.bind(new InetSocketAddress("127.0.0.1",8080));
        /**
         * 第三步：构造Selector
         */
        Selector selector = Selector.open();
        /**
         * 第四步：Channel注册到Selector中
         */
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        //第五步：自旋，实现Selector监听并且具体事件（实现Channel具体逻辑）
        while(true){
            //若没有事件激活，则处理阻塞
            selector.select();
            //获取事件对应的KEYS
            Set<SelectionKey> selectionKeys =  selector.selectedKeys();
            if(selectionKeys.size() == 0){//事件为空则直接继承等待
                continue;
            }
            //遍列事件KEY
            for (SelectionKey selectionKey : selectionKeys){
                //Accept事件（客户端发起的连接事件）
                if(selectionKey.isAcceptable()){
                    System.out.println("accept .... ....");
                    //获取客户端channel
                    SocketChannel clientChannel =  serverChannel.accept();
                    //注册客户端channel
                    if(null != clientChannel){
                        //设置客户端属性非阻塞
                        clientChannel.configureBlocking(false);
                        //连接事件之后就是注册客户端READ事件
                        clientChannel.register(selector,SelectionKey.OP_READ); }
                }
                //当数据可读时
                if(selectionKey.isReadable()){
                    System.out.println("模拟业务逻辑处理... ");
                    //获取客户端channel
                    SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                    //注册客户端channel
                    if(null != clientChannel){
                        //可以设置新线程去读取然后处理
                        buffer.clear();
                        while (clientChannel.read(buffer) != -1){
                           byte[] bytes = buffer.array();
                           System.out.println("模拟业务逻辑处理... " + new String(bytes));
                           buffer.clear();
                           buffer.put("Hello ,client".getBytes() );
                           buffer.flip();// 这一步必须有
                           clientChannel.write(buffer);
                       }
                       buffer.clear();
                    }
                }
            }
        }

    }
}
