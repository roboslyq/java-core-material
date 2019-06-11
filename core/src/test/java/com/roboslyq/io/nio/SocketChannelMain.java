package com.roboslyq.io.nio;

import java.io.IOException;

public class SocketChannelMain {
    public static void main(String[] args) throws InterruptedException {
        SocketChannelDemo client = new SocketChannelDemo();
        client.clientService(8080);
    }
}
