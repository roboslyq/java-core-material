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

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;

/**
 *〈
 * Scatter: 分散，即读数据时，可以传入一个Buffer数组，可以将数据依次读入到数组中。这个在自定义协议时很有用。
 *      比如自定义协议有Head,body,tail三部分。我们可以通过Scatter手段直接到这三部分数据读入到三个不同的Buffer中，从而不需要使用Substr截取。
 *
 * Gather：聚集，与Scatter相反，可以将多个Buffer中的数据依次输出。
 * 〉
 *
 * @author roboslyq
 * @date 2020/4/9
 * @since 1.0.0
 */
public class ScatterAndGather {

    public static void main(String[] args) throws Exception {
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
            Arrays.asList(byteBuffers).stream().
                    map(buffer -> buffer.toString()).
                    forEach(System.out::println);

        }

        //将所有buffer都flip。
        Arrays.asList(byteBuffers).
                forEach(buffer -> {
                    buffer.flip();
                });
        System.out.println(getString(byteBuffers[0]));
        System.out.println(getString(byteBuffers[1]));
        System.out.println(getString(byteBuffers[2]));

        //将所有buffer都clear
        Arrays.asList(byteBuffers).
                forEach(buffer -> {
                    System.out.println(byteBuffers.toString());
                    buffer.clear();
                });


    }

    public static String getString(ByteBuffer buffer) {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try {
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();
            // charBuffer = decoder.decode(buffer);//用这个的话，只能输出来一次结果，第二次显示为空
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return String.valueOf(charBuffer.array());
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}