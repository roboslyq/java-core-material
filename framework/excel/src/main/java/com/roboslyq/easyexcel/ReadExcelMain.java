package com.roboslyq.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.roboslyq.easypoi.EasypoiMain;

import java.io.File;
import java.net.URL;

/**
 * @Author roboslyq
 * @desc
 * @since 2021/12/20 0:14
 */
public class ReadExcelMain {
    public static void main(String[] args) {
//        String fileName =  "D:\\IdeaProjects_community\\java-core-material\\framework\\excel\\src\\main\\resources\\demo.xlsx";
        URL url= EasypoiMain.class.getClassLoader().getResource("demo.xlsx");
        String  fileName  = url.getFile();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }
}
