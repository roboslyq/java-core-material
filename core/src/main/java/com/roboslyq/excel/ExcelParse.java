package com.roboslyq.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;

/**
 * @author roboslyq
 * @title: ExcelParse
 * @projectName java-core-material
 * @description: TODO
 * @date 2021/11/1916:00
 */
public class ExcelParse {
    public static void main(String[] args) {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName =  "data.xls";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                System.out.println(demoData);
            }
        })).sheet().doRead();

    }
}
