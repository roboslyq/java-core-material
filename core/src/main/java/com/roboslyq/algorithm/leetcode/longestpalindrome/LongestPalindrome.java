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
//        String test1 ="abcbaaabc";
//        String test2 ="babad";
//        String test2 ="cbbd";
//        String test2 = "ccd";
//        String test2 = "ccc";
//        String test2 = "abcda";
        String pre =   "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa";
        String test2 = "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa";
//        String test2 = "bbbb";
//       String test3 ="bb";
//        System.out.println("abcd".substring(1,2));
        System.out.println(palindrome.longestPalindrome(test2));
//        System.out.println(palindrome.longestPalindrome(test3));
    }
    public String longestPalindrome(String s) {
        if(s == null || s.equals("") || s.length() == 1){
            return s;
        }else if(s.length() == 2 ){
            if(s.charAt(0) == s.charAt(1)){
                return s;
            }else{
                return s.charAt(1) + "";
            }
        }else{
            int resultStart = 0;
            int resultEnd = 0;
            int before = 0;
            int after = 0;
            for (int i = 0; i < s.length(); i++) {
                if(i==496){
                    System.out.println(i);
                }
                if(i == 0){
                    int tmp  = i;
                    while (s.charAt(i) == s.charAt(++tmp)) {
                        after ++;
                        if( tmp == s.length()-1){
                            break;
                        }

                    }
                    resultStart = before;
                    resultEnd = after;
                    continue;
                }
                if(i + 1 == s.length()){
                    break;
                }
                int forTimes = 0;
                if(s.charAt( i - 1) == s.charAt(i + 1)) ++forTimes;
                if(s.charAt( i ) == s.charAt(i + 1)) ++forTimes;
                for(int j = 0; j< forTimes;j ++  ){
                    int tmpBefore;
                    int tmpAfter ;
                    if(s.charAt( i - 1) == s.charAt(i + 1) && j == 0){
                        tmpBefore = before = i ;
                        tmpAfter = after = i ;

                    }else{
                        after = tmpAfter = i+ 1;
                        before = tmpBefore = i;
                    }
                    if(tmpAfter < s.length()-1 && tmpBefore > 0){

                        while (s.charAt(--tmpBefore) == s.charAt(++tmpAfter)) {
                            before--;
                            after++;
                            if (before == 0 ) {
                                break;
                            }
                            if (after == s.length()-1) {
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
            return  s.substring(resultStart,resultEnd+1);
        }
    }
}