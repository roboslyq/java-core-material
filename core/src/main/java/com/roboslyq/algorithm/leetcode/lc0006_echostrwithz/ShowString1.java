package com.roboslyq.algorithm.leetcode.lc0006_echostrwithz;

import java.util.ArrayList;
import java.util.List;

/**
 * 思想：
 * 想像结果是一个二维数组，按顺序把字符串的每个字符填入二维数组中。
 * 循环以行数为基础，当行数到最大值时开始递减。行数为0行开始递增。
 */
public class ShowString1 {
    public static void main(String[] args) {
        ShowString1 ss = new ShowString1();
        ss.convert("abcd",2);

    }
    public String convert(String s, int numRows){
        List<StringBuffer> stringBuffers = new ArrayList<>();
        if(s == null || s.length() <=1 || numRows ==1 ){
            return  s;
        }
        for(int i = 0; i< numRows ;i++){
            stringBuffers.add(new StringBuffer());
        }
        boolean isAdd = true;
        int currentRowNum = 0;
        for(int i = 0; i < s.length(); i++) {
            stringBuffers.get(currentRowNum).append(s.charAt(i));
            if(currentRowNum == numRows - 1){
                isAdd = false;
            }
            if(currentRowNum == 0){
                isAdd = true;
            }
            currentRowNum = isAdd ? currentRowNum + 1 : currentRowNum - 1;
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < numRows ;i++){
            res.append(stringBuffers.get(i).toString());
        }
        return  res.toString();
        }

    }
