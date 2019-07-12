/**
 * Copyright (C), 2015-2019
 * FileName: LongestPalindrome
 * Author:   luo.yongqian
 * Date:     2019/7/11 16:48
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/7/11 16:48      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.longestpalindrome;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/7/11
 * @since 1.0.0
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome palindrome = new LongestPalindrome();
        String test ="abcbaaabc";
//        String test ="babad";
//        String test ="bb";
        System.out.println(palindrome.longestPalindrome(test));
    }
    public String longestPalindrome(String s) {
        if(s == null || s.equals("") || s.length() == 1){
            return s;
        }
        if(s.length() == 2 ){
            if(s.charAt(1) == s.charAt(2)){
                return s;
            }else{
                return s.charAt(1) + "";
            }
        }
        int resultStart = 0;
        int resultEnd = 0;
        int before = 0;
        int after = 0;
        for (int i = 1; i < s.length(); i++) {
            if(i + 1 == s.length()){
                break;
            }
            int forTimes = 0;
            if(s.charAt( i - 1) == s.charAt(i + 1)) ++forTimes;
            if(s.charAt( i ) == s.charAt(i + 1)) ++forTimes;
            for(int j = 0; j< forTimes;j ++  ){
                if(s.charAt( i - 1) == s.charAt(i + 1)){
                    before = i - 1;
                    after = i + 1;
                }else{
                    after = i+ 2;
                }
                if(after < s.length() && before > 0){

                    while (s.charAt(before) == s.charAt(after)) {
                        before--;
                        after++;
                        if (before < 0 || after == s.length()) {
                            break;
                        }
                    }
                }
                if( after  - before > resultEnd - resultStart ){
                    resultStart = before;
                    resultEnd = after;
                }
            }

        }
        if(resultStart == resultEnd) resultEnd++;//默认取第一个字母
        return  s.substring(resultStart+1,resultEnd);
    }
}