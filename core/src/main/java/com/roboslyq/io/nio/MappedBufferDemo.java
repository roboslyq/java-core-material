/**
 * Copyright (C), 2015-2020
 * FileName: MappedBufferDemo
 * Author:   luo.yongqian
 * Date:     2020/4/10 9:06
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/4/10 9:06      1.0.0               创建
 */
package com.roboslyq.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/4/10
 * @since 1.0.0
 */
public class MappedBufferDemo {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("test.txt","rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE,0,26);
        byte[] buffer1 =new byte[10];
        mappedByteBuffer.get(buffer1);
        System.out.println(new String(buffer1));
        mappedByteBuffer.flip();
        mappedByteBuffer.put("abcde".getBytes());
    }

}