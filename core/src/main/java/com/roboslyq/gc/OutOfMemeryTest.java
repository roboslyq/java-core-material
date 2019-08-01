package com.roboslyq.gc;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemeryTest {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        while (true){
            System.out.println(i++);
            byte[] b = new byte[1024*1024];
            list.add(b);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
