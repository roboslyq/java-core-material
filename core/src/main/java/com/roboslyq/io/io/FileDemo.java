package com.roboslyq.io.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 1、在1.4 之前的IO中，主要用途是文件的读取。和net相关的IO还在net包中。
 * 2、文件的读取也是基于IO实现的
 * 3、File本身是对文件的抽象，可以删除和新增及查看相关属性。但本身没有修改文件内容的方法。
 *      可以借用包装模式（使用具体流比如果FileWriter等来实现读写），或者直接使用RandomAccessFile实现读写
 *
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
        FileDemo fileDemo = new FileDemo();
       // fileDemo.fileStream("D://fileDemo.txt");
        fileDemo.fileWriter("D://fileDemo.txt");
    }

    /**
     * 字节码操作文件
     * @param filePath
     * @throws IOException
     */
    public void fileStream(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()){
            boolean createNewFileResult = file.createNewFile();
            if(!createNewFileResult){
                System.out.println("文件创建失败!!");
                return;
            }
        }
        FileOutputStream out = new FileOutputStream(file);
        for(int i = 0 ; i < 10; i++ ){
            out.write((i + "\n\r").getBytes());
        }

    }

    /**
     * 使用字符流
     */
    public void fileWriter(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()){
            boolean createNewFileResult = file.createNewFile();
            if(!createNewFileResult){
                System.out.println("文件创建失败!!");
                return;
            }
        }
        //覆盖写
        FileWriter out = new FileWriter(file);
        //追加写
//        FileWriter out = new FileWriter(file,true);
        for(int i = 0 ; i < 10; i++ ){
            out.write(i + System.getProperty("line.sparator"));
        }
        //必须刷新，否则部分数据无法写回文件。
        // 注意，是部分数据，当达到缓冲区存储值，会自动 刷新缓冲区。
        out.flush();
    }
}
