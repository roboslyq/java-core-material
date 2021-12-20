package com.roboslyq.easyexcel;

import java.io.File;

/**
 * @Author roboslyq
 * @desc
 * @since 2021/12/20 0:14
 */
public class ReadExcelMain {
    public static void main(String[] args) {
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }
}
