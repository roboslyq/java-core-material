package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0028_strstr;

public class StrStr {
    public static void main(String[] args) {

    }
    public int strStr(String haystack, String needle) {
        if(haystack == null ){
            return -1;
        }else{
            //具体实现可以参考String.indexOf源码实现 ，主要是利用了字符数组来处理
            return haystack.indexOf(needle);
        }
    }
}
