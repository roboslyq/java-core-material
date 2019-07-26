package com.roboslyq.algorithm.leetcode.longestpalindrome_0005;

import java.util.Vector;

/**
 * 思想与中心法基本一致，但在计算时，复用了回文窜对称的特性，少算了一部分数据。
 * 比如：a .... b .....a
 * 如果上面的字符串是回文串，并且b是左右a的中心点。如果左a的回文串长度是n,那么右边a的回文串长度应该>=n，因为对称特性
 *
 */
public class LongestPalindrome1 {
    public static void main(String[] args) {
        LongestPalindrome1 longestPalindrome = new LongestPalindrome1();
        System.out.println(longestPalindrome.longestPalindrome("bbb"));
        System.out.println(longestPalindrome.longestPalindrome("abcbaaabc"));
        System.out.println(longestPalindrome.longestPalindrome("babad"));
        System.out.println(longestPalindrome.longestPalindrome("cbbd"));
        System.out.println(longestPalindrome.longestPalindrome("ccd"));
        System.out.println(longestPalindrome.longestPalindrome("ccc"));
        System.out.println(longestPalindrome.longestPalindrome("abcda"));
        //        String test1 ="abcbaaabc";
//        String test2 ="babad";
//        String test2 ="cbbd";
//        String test2 = "ccd";
//        String test2 = "ccc";
//        String test2 = "abcda";
    }

    public String longestPalindrome(String s) {
        StringBuilder str_result = new StringBuilder("#");
        for(int i = 0;i < s.length(); ++i){
            str_result.append(s.charAt(i));
            str_result.append("#");
        }
        int max_mid_pos = 0;
        int max_length = 0;
        int mid_pos = 0;
        int last_right_max_pos = 0;
        Vector<Integer> pb = new Vector<>(str_result.length(),0);
        for(int i = 0;i<str_result.length();i++){
            pb.add(i,0);
        }
        for(int i = 1;i < str_result.length(); ++i){
             if(last_right_max_pos > i) {
                 if( pb.get(2*mid_pos - i) > last_right_max_pos - i) {
                      pb.add(i,last_right_max_pos - i);
                 } else{
                     pb.add(i, pb.get(2*mid_pos - i));
                 }
             }
             else{
                 pb.add(i,1);
             }
            // 使用 ? :三元运算符替代 if else，能降低一些执行用时
//            pb.add( last_right_max_pos > i ? (pb.get(2 * mid_pos - i) > last_right_max_pos - i ? last_right_max_pos - i : pb.get(2*mid_pos - i)) : 1);
            Integer valuePb = pb.get(i);
            if(i + valuePb <= str_result.length() -1 && i - valuePb>= 0) {
                while (str_result.charAt(i + valuePb) == str_result.charAt(i - valuePb)) {
                    ++valuePb;
                    pb.add(i, valuePb);
                    if (i + valuePb > str_result.length() - 1 || i - valuePb < 0) {
                        break;
                    }
                }
            }
            if(last_right_max_pos -i < valuePb) {
                last_right_max_pos = i + valuePb;
                mid_pos = i;
            }
            if(max_length < valuePb) {
                max_length = valuePb;
                max_mid_pos = i;
            }

        }
        return s.substring((max_mid_pos - max_length) / 2,max_length - 1);
    }

}