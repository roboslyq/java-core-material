/**
 * Copyright (C), 2015-2019
 * FileName: IsPalindrome
 * Author:   luo.yongqian
 * Date:     2019/9/27 16:28
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/27 16:28      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0125_ispalindrome;

/**
 *
 * 〈给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。〉
 * @author luo.yongqian
 * @create 2019/9/27
 * @since 1.0.0
 */
public class IsPalindrome {
    public static void main(String[] args) {
        String test = "A man, a plan, a canal: Panama";
//        String test = ",.";
        IsPalindrome isPalindrome = new IsPalindrome();
        System.out.println(isPalindrome.isPalindrome(test));;

    }

    /**
     * 双指针法
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if(s == null || s.length()  == 0) return true;
        s = s.toLowerCase();
        int leftIndex = 0;
        int rightIndex = s.length()-1;
        char left ;
        char right ;
        boolean  res  = true;
        while (leftIndex < rightIndex){
            left = s.charAt(leftIndex);
            while(!isAvableChar(left)  && leftIndex < rightIndex){
                left =  s.charAt(++leftIndex);
            }
            right = s.charAt(rightIndex);
            while(!isAvableChar(right) && leftIndex< rightIndex){
                right =  s.charAt(--rightIndex);
            }
            if(left  !=  right){
                res = false;
                break;
            }
            leftIndex++;
            rightIndex--;
        }
        return res;
    }
    public boolean isAvableChar(char chr){
        return ('0' <=  chr && chr  <= '9') ||  ('a'<=chr  && chr<='z');
    }
}