package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0065_isnumber;

import java.util.regex.Pattern;

/**
 * 使用正则表达式
 */
public class IsNumber {
    public static void main(String[] args) {
        //[-|+]?(0-9)+[.]?(0-9)*[e]+(0-9)*
        IsNumber isNumber = new IsNumber();
        System.out.println(isNumber.isNumber("+0.0"));

    }
    public boolean isNumber(String s) {
        if(s == null || s.trim().length() == 0) return false;
        Pattern pattern =  Pattern.compile("^[+|-]?([0-9]+\\.[0-9]+|\\.[0-9]+|[0-9]+\\.|[0-9]+)(e[+|-]?[0-9]+)?$");
        return pattern.matcher(s).matches();
    }
}
