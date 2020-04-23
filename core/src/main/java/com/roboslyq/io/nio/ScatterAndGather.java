/**
 * Copyright (C), 2015-2020
 * FileName: ScatterAndGather
 * Author:   luo.yongqian
 * Date:     2020/4/9 23:08
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/4/9 23:08      1.0.0               创建
 */
package com.roboslyq.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;

/**
 * 〈
 * Scatter: 分散，即读数据时，可以传入一个Buffer数组，可以将数据依次读入到数组中。这个在自定义协议时很有用。
 * 比如自定义协议有Head,body,tail三部分。我们可以通过Scatter手段直接到这三部分数据读入到三个不同的Buffer中，从而不需要使用Substr截取。
 * <p>
 * Gather：聚集，与Scatter相反，可以将多个Buffer中的数据依次输出。
 * 〉
 *
 * @author roboslyq
 * @date 2020/4/9
 * @since 1.0.0
 */
public class ScatterAndGather {

    public static void main(String[] args) throws Exception {
        scatterRead();
        gatherWrit();
    }

    private static void gatherWrit() throws Exception {
        FileOutputStream file = new FileOutputStream("test.txt");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(3);
        byteBuffers[1] = ByteBuffer.allocate(4);
        byteBuffers[2] = ByteBuffer.allocate(5);
        //写入byteBuffer
        byteBuffers[0].put("abc".getBytes());
        byteBuffers[1].put("abcd".getBytes());
        byteBuffers[2].put("abcde".getBytes());
        //需要Flip
        byteBuffers[0].flip();
        byteBuffers[1].flip();
        byteBuffers[2].flip();
        //从ByteBuffer中读到文件
        fileChannel.write(byteBuffers);

    }

    /**
     * 分散读
     *
     * @throws IOException
     */
    private static void scatterRead() throws IOException {
        Charset charset = Charset.forName("UTF-8");

        FileInputStream file = new FileInputStream("README.md");
        FileChannel channel = file.getChannel();
        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(3);
        byteBuffers[1] = ByteBuffer.allocate(4);
        byteBuffers[2] = ByteBuffer.allocate(5);
        int totalSize = 3 + 4 + 5;
        int readedSize = 0;
        while (readedSize < totalSize) {
            long readCount = channel.read(byteBuffers);
            readedSize += readCount;
            System.out.println("readedSize : " + readedSize);
            //通过流打印
            /*
                java.nio.HeapByteBuffer[pos=3 lim=3 cap=3]
                java.nio.HeapByteBuffer[pos=4 lim=4 cap=4]
                java.nio.HeapByteBuffer[pos=5 lim=5 cap=5]
             */
            Arrays.stream(byteBuffers).
                    map(ByteBuffer::toString).
                    forEach(System.out::println);

        }

        //将所有buffer都flip。
        Arrays.asList(byteBuffers).
                forEach(ByteBuffer::flip);
        //将所有buffer都clear
        Arrays.asList(byteBuffers).
                forEach(buffer -> {
                    System.out.println(new String(buffer.array()));
                    buffer.clear();
                });
    }
}