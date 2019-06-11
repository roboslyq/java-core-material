package com.roboslyq.io.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\1.txt");
        System.out.println(path.getClass());
    }
}
