package com.roboslyq.java.java8.stream;

import java.util.Random;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) {
        Stream.generate(()-> {
                    System.out.println("开始生产");
                    return new Random().nextInt(10);}
            )
                .limit(10)
                .filter(val -> {
                    System.out.println("开始过滤");
                    return val > 3;}
                )
                .forEach(val ->{
                    System.out.println("开始最终结果处理");
                    System.out.println(val);
                });
    }
}
