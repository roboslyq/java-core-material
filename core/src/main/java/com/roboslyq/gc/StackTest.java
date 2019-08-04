package com.roboslyq.gc;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * 目的：测试栈异常
 * 1、(Jdk9)未配置-Xss参数时，日志如下：
 * ... ...
 * 9857
 * 9858
 * 9859
 * 9860
 * Exception in thread "main" java.lang.StackOverflowError
 * 	at java.base/sun.nio.cs.UTF_8$Encoder.encodeLoop(UTF_8.java:695)
 * 	at java.base/java.nio.charset.CharsetEncoder.encode(CharsetEncoder.java:578)
 * 	at java.base/sun.nio.cs.StreamEncoder.implWrite(StreamEncoder.java:292)
 * 	at java.base/sun.nio.cs.StreamEncoder.implWrite(StreamEncoder.java:281)
 * 	at java.base/sun.nio.cs.StreamEncoder.write(StreamEncoder.java:125)
 * 	at java.base/java.io.OutputStreamWriter.write(OutputStreamWriter.java:211)
 * 	at java.base/java.io.BufferedWriter.flushBuffer(BufferedWriter.java:120)
 * 	at java.base/java.io.PrintStream.write(PrintStream.java:526)
 * 	at java.base/java.io.PrintStream.print(PrintStream.java:597)
 * 	at java.base/java.io.PrintStream.println(PrintStream.java:733)
 * 	at com.roboslyq.gc.StackTest.recursive(StackTest.java:14)
 * 	at com.roboslyq.gc.StackTest.recursive(StackTest.java:16)
 * 2、(Jdk9)配置-Xss1M参数时，日志如下：
 */
public class StackTest {
    public static void main(String[] args) {
        StackTest stackTest = new StackTest();
        Runtime runtime = Runtime.getRuntime();
        //钩子函数(关机时钩子函数)
        runtime.addShutdownHook(new Thread(() -> {
            System.out.println("shut down hook;");
        }));
        //获取运行时的VM参数
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        for(String arg : runtimeMXBean.getInputArguments()){
            if (arg.toUpperCase().contains("XSS")){
                System.out.println(arg);
            }
        }
        //递归调用
        stackTest.recursive(0,Integer.MAX_VALUE);
    }
    public void recursive(int startIndex,int endIndex){
        Long a ,b,c,d,e,f;
        a = 100L;
        b = 100L;
        c = 100L;
        d = 100L;
        e = 100L;
        f = 100L;
        if(startIndex==endIndex) return;
        System.out.println(startIndex);
        startIndex++;
        recursive(startIndex,endIndex);
    }
}
