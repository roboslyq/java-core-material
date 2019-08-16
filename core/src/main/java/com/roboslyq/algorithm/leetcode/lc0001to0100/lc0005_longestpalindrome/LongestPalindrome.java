/**
 * Copyright (C), 2015-2019
 * FileName: LongestPalindrome1
 * Author:   luo.yongqian
 * Date:     2019/7/11 16:48
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/7/11 16:48      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0005_longestpalindrome;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/7/11
 * @since 1.0.0
 * 中心法：循环遍列，从中心往两边扩散
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome palindrome = new LongestPalindrome();
//        String test1 ="abcbaaabc";
//        String test2 ="babad";
//        String test2 ="cbbd";
//        String test2 = "ccd";
//        String test2 = "ccc";
//        String test2 = "abcda";
        String pre =   "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa";
        String test2 = "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa";
//        String test2 = "bbbb";
       String test3 ="bbb";
//        System.out.println("abcd".substring(1,2));
        System.out.println(palindrome.longestPalindrome(test2));
        System.out.println(palindrome.longestPalindrome(test3));
//        System.out.println(palindrome.longestPalindrome(test3));
    }
    public String longestPalindrome(String s) {
        //特殊情况特殊处理
        if(s == null || s.equals("") || s.length() == 1){
            return s;
        }
        else{
            int resultStart = 0;
            int resultEnd = 0;
            int before = 0;
            int after = 0;
            for (int i = 0; i < s.length() - 1; i++) {
                //判断前N位连续的情况，例如 "AAAAAbxbb"的结果是"AAAAA"。因为后续的算法是往两边和往右比较，处理不了这个情况
                if(i == 0){
                    int tmp  = i;
                    while (s.charAt(i) == s.charAt(++tmp)) {
                        after ++;
                        if( tmp == s.length()-1) break;
                    }
                    resultStart = before;
                    resultEnd = after;
                    continue;
                }
                int forTimes = 0;
                //两边相等(exp: "aba")，符合条件，需要循环
                final boolean b = s.charAt(i - 1) == s.charAt(i + 1);
                if(b) ++forTimes;
                //右边相等（exp: "aa"）
                if(s.charAt( i ) == s.charAt(i + 1)) ++forTimes;
                for(int j = 0; j< forTimes;j ++  ){
                    int tmpBefore , tmpAfter;
                    if(b && j == 0){
                        tmpBefore = before = tmpAfter = after = i ;
                    }else{
                        after = tmpAfter = i+ 1;
                        before = tmpBefore = i;
                    }
                    if(tmpAfter < s.length()-1 ){
                        while (s.charAt(--tmpBefore) == s.charAt(++tmpAfter)) {
                            before--; after++;
                            if (before == 0 || after == s.length()-1 ) break;//结束条件
                        }
                    }
                    //有更大的符合条件的回文串产生
                    if( after  - before > resultEnd - resultStart ){
                        resultStart = before;
                        resultEnd = after;
                    }
                }
            }
            return  s.substring(resultStart,resultEnd+1);
        }
    }
}