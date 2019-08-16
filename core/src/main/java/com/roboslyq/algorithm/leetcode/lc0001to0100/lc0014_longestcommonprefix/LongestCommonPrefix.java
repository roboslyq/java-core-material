package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0014_longestcommonprefix;

/**
 * 下面解法在leetcode中审题有误，并不是查找公共的前缘，字符串中最长的公共串，包含前缀。
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
//        System.out.println( longestCommonPrefix.longestCommonPrefix(new String[]{"a", "b", "c"}));
//        System.out.println( longestCommonPrefix.longestCommonPrefix(new String[]{}));
        System.out.println( longestCommonPrefix.longestCommonPrefix(new String[]{"aca","cba"}));


    }
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }
        int charIndex = 0;
        /* 1 表示位相等，0表表示不相等*/
        int commonStart = charIndex;
        int commonEnd = charIndex;
        int currentStart = charIndex;
        int currentEnd = charIndex;
        boolean hasMore = true;
        //hasMore：是否有更多的元素，即字符串数组中所有的字符串还有更多的元素。
        while (hasMore){
            char charAtI = '@';
            boolean allEqual = true;
            for(String str : strs){
                if(charIndex < str.length()){
                    if('@' == charAtI){// for的第一次循环，赋值
                        charAtI = str.charAt(charIndex);
                    }else{//正常循环，判断当前charAtI是否与str.charAt(charIndex)是否相等
                        if(str.charAt(charIndex) != charAtI) {
                            allEqual = false;
                            break;
                        }
                    }
                }else{//有字符串已经结束，结束整个循环
                    hasMore = false;
                    allEqual = false;
                    break;
                }
            }
            charIndex++;
            if(allEqual){
                currentEnd++;
            }else{
                if(currentEnd - currentStart > commonEnd - commonStart){
                    commonEnd = currentEnd;
                    commonStart = currentStart;
                    currentStart = ++currentEnd;
                }
            }
        }
        //返回结果
        return strs[0].substring(commonStart,commonEnd);
    }
}
