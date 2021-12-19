package com.roboslyq.excel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author roboslyq
 * @title: DemoData
 * @projectName java-core-material
 * @description: TODO
 * @date 2021/11/1916:05
 */
@Getter
@Setter
@EqualsAndHashCode
public class DemoData {
    private String string;
    private String date;
    private String doubleData;

    @Override
    public String toString() {
        return "DemoData{" +
                "string='" + string + '\'' +
                ", date=" + date +
                ", doubleData=" + doubleData +
                '}';
    }
}