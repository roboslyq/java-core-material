package com.roboslyq.io.nio;

import java.io.IOException;

public class ServerSocketChannelMain {
    public static void main(String[] args) {
        ServerSocketChannelDemo server = new ServerSocketChannelDemo();
        try {
            server.bootStrapServer(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
