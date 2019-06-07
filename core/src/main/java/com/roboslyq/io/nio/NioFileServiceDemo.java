package com.roboslyq.io.nio;

import jdk.dynalink.StandardOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 1、NIO模型有3个很重要的概念channel,buffer和selector
 * 2、
 */
public class NioFileServiceDemo {
    public static void main(String[] args) throws IOException {
        NioFileServiceDemo nioFileServiceDemo = new NioFileServiceDemo();
        nioFileServiceDemo.operatorFileByChannel("D://fileDemo.txt","D://a.txt");
        nioFileServiceDemo.test3();
    }
    /**
     * 利用channel实现文件复制操作
     * 1、可以通过FileChannel.open静态方法借助FilePaths工具类实现构建。也可以从文件流中构建
     */
    public void operatorFileByChannel(String sourceFilePathAndName,String targetFileNameAndPath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(sourceFilePathAndName));
        //从流构建FileChannel
        FileChannel fileChannel = fileInputStream.getChannel();
        //从静态方法中构造FileChannel
        FileChannel fileChannelOut = FileChannel.open(Paths.get(targetFileNameAndPath), StandardOpenOption.READ,StandardOpenOption.WRITE);
        //设置读取缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(2000);
        StringBuilder stringBuffer = new StringBuilder();
        //
        while(fileChannel.read(byteBuffer) != -1){
            byteBuffer.flip();
            fileChannelOut.write(byteBuffer);
            System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
            //可以使用StandardCharsets.UTF-8或者Charset.forName("UTF-8")
            stringBuffer.append(new String(byteBuffer.array(),StandardCharsets.UTF_8 ));
            byteBuffer.clear();

        }
        System.out.println(stringBuffer.toString());
    }

    /**
     *
     *利用直接缓冲区完成文件的复制（内存映射文件）
     */
    public void test2() throws IOException {
        FileChannel inputChannel = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);
        FileChannel outputChannel = FileChannel.open(Paths.get("2.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
        MappedByteBuffer inputMapperByteBuffer = inputChannel.map(FileChannel.MapMode.READ_ONLY,0,inputChannel.size());
        MappedByteBuffer outputMapperByteBuffer = outputChannel.map(FileChannel.MapMode.READ_WRITE,0,inputChannel.size());
        byte[] bytes = new byte[inputMapperByteBuffer.limit()];
        inputMapperByteBuffer.get(bytes);
        outputMapperByteBuffer.put(bytes);
        outputChannel.close();
        inputChannel.close();
        outputChannel.close();
        inputChannel.close();
    }

    /**
     * 直接通过通道传输数据(直接缓冲区传输),速度快方便。
     * @throws IOException
     */
    public void test3() throws IOException {
        FileChannel inputChannel = FileChannel.open(Paths.get("D:\\1.txt"), StandardOpenOption.READ);
        FileChannel outputChannel = FileChannel.open(Paths.get("D:\\2.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

        inputChannel.transferTo(0,inputChannel.size(),outputChannel);

    }
}
