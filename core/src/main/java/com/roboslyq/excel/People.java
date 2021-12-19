package com.roboslyq.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author roboslyq
 * @title: People
 * @projectName java-core-material
 * @description: TODO
 * @date 2021/11/1916:17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class People {
    private String name;
    private String age;
    private String sex;
    private String area;
}
