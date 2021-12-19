package com.roboslyq.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * @author roboslyq
 * @title: ExcelParse
 * @projectName java-core-material
 * @description: TODO
 * @date 2021/11/1916:00
 */
public class ExcelParsePOI {
    public static void main(String[] args) throws IOException {
            //获取工作簿
            XSSFWorkbook book = new XSSFWorkbook("data.xlsx");
            //获取工作表
            XSSFSheet sheet = book.getSheetAt(0);
//        //第一种读取读取所有数据，实际中不需要
//        //获取行
        for (Row row : sheet) {
            //获取单元格
            for (Cell cell : row) {
                //获取单元格中的内容
                cell.setCellType(CellType.STRING);
                System.out.println(cell.getCellComment());
            }
        }
    }
}
