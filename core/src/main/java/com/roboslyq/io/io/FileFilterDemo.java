package com.roboslyq.io.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
public class FileFilterDemo {
    public static void main(String[] args) {
        File dir = new File("D:\\helloworld");
        FilenameFilter  filenameFilter = (file,name) -> name.equals("a.txt");
        File[] fileList  = dir.listFiles(filenameFilter);
        assert fileList != null;
        Arrays.stream(fileList).forEach(System.out::println);

        File[] fileList1  = dir.listFiles(file -> file.getName().equals("a.txt"));
        assert fileList != null;
        Arrays.stream(fileList).forEach(System.out::println);
    }
}
