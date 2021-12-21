/**
 * Copyright (C), 2015-2021
 * FileName: easypoi
 * Author:   luo.yongqian
 * Date:     2021/12/20 23:59
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2021/12/20 23:59      1.0.0               创建
 */
package com.roboslyq.easypoi;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import cn.afterturn.easypoi.exception.excel.ExcelImportException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2021/12/20
 * @since 1.0.0
 */
@Slf4j
public class EasypoiMain {
    public static void main(String[] args) throws IOException, URISyntaxException {
//        File file = new File("D:\\IdeaProjects_community\\java-core-material\\framework\\excel\\src\\main\\resources\\demo.xlsx");
        ImportParams importParams =  new ImportParams();
        URL url= EasypoiMain.class.getClassLoader().getResource("demo.xlsx");
        importParams.setHeadRows(1);
        List<DemoData> demoDatas = importExcel(new File(url.toURI()),DemoData.class,importParams);
        demoDatas.forEach(System.out::println);
    }
    /**
     * Excel 导入 数据源本地文件,不返回校验结果 导入 字 段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param file
     * @param pojoClass
     * @param params
     * @return
     */
    public static <T> List<T> importExcel(File file, Class<?> pojoClass, ImportParams params) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            return new ExcelImportService().importExcelByIs(in, pojoClass, params, false).getList();
        } catch (ExcelImportException e) {
            throw new ExcelImportException(e.getType(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ExcelImportException(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}