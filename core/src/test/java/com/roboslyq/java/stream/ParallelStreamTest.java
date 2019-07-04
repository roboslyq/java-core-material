package com.roboslyq.java.stream;

import java.util.Random;
import java.util.stream.Stream;

public class ParallelStreamTest {
    public static void main(String[] args) {
        Stream.generate(()->{
           Random random = new Random();
           Integer res = random.nextInt(100);
           print("生产元素:" + res);
           return res;
        })
        .peek(val ->{
            print("peek 元素 ：" + val);
        })
                .limit(100)
        .parallel()
        .forEach(
                val ->{
                    print("foreach 元素 ：" + val);
                }
        );
        ;
    }
    public static void print(Object obj){
        System.out.println("[thread-" + Thread.currentThread().getId() + "]" + obj);
    }
}

