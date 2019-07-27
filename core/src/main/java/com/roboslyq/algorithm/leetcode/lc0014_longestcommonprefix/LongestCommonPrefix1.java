package com.roboslyq.algorithm.leetcode.lc0014_longestcommonprefix;

/**
 * 下面解法在leetcode中审题有误，并不是查找公共的前缘，字符串中最长的公共串，包含前缀。
 */
public class LongestCommonPrefix1 {
    public static void main(String[] args) {
        LongestCommonPrefix1 longestCommonPrefix = new LongestCommonPrefix1();
//        System.out.println( longestCommonPrefix.longestCommonPrefix(new String[]{"a", "b", "c"}));
//        System.out.println( longestCommonPrefix.longestCommonPrefix(new String[]{}));
        System.out.println( longestCommonPrefix.longestCommonPrefix(new String[]{"aca","cba"}));


    }
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 1){
            return strs[0];
        }
        boolean hasMore = true;
        int currentIndex = 0;
        String res="";
        while (hasMore){
            char charAtI = '@';
            for (String str : strs){
                if(currentIndex < str.length()) {
                    if (charAtI == '@') {
                        charAtI = str.charAt(currentIndex);
                    } else {
                        if (charAtI != str.charAt(currentIndex)) {
                            hasMore = false;
                        }
                    }
                }else{
                    hasMore = false;
                }
            }
            if(!hasMore){
                res = strs[0].substring(0,currentIndex);
            }else{
                currentIndex++;
            }
        }
        return  res;
    }
}
