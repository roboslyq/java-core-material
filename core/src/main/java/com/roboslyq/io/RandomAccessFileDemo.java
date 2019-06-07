package com.roboslyq.io;

import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    public static void main(String[] args) throws Exception {
        RandomAccessFileDemo randomAccessFileDemo = new RandomAccessFileDemo();
        randomAccessFileDemo.creatFile("D://fileDemo.txt");
    }

    /**
     * 1、RandomAccessFile，默认从0开始往后覆盖写，因为还没有写到位置的原有字符串仍保留。
     *
     * @param fileNameAndPath
     * @throws Exception
     */
    public void creatFile(String fileNameAndPath) throws Exception {
        //构造方式一：通过文件路径名称自动创建文件，如果文件不存在，则抛出FileNotFoundException
        RandomAccessFile file = new RandomAccessFile(fileNameAndPath, "rw");
        //构造方式二：通过已经存在的文件File，进行构建
//        RandomAccessFile file = new RandomAccessFile(new File(fileNameAndPath),"r");
        System.out.println("file.channel" + file.getChannel());
        System.out.println("file.channel" + file.getFD());
        System.out.println("file.channel" + file.getFilePointer());
        //通过seek调整文件中的访问位置
        file.seek(file.length());
        for (int i = 0; i < 12; i++) {
            file.writeBytes(i + System.getProperty("line.separator"));
        }
    }
}
