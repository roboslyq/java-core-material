package com.roboslyq.file;

import lombok.SneakyThrows;

import java.io.RandomAccessFile;

public class FileDemo {
    @SneakyThrows
    public static void main(String[] args) {
        RandomAccessFile accessConfFile = new RandomAccessFile("D://confFile.conf", "rw");
        // 把该分段标记为 true 表示完成
        accessConfFile.setLength(100L);
        accessConfFile.seek(10l);
        accessConfFile.write(Byte.MAX_VALUE);
        accessConfFile.close();
    }
}
