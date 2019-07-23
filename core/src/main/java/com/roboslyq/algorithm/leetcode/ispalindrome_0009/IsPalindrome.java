/**
 * Copyright (C), 2015-2019
 * FileName: IsPalindrome
 * Author:   luo.yongqian
 * Date:     2019/7/23 12:20
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/7/23 12:20      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.ispalindrome_0009;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/7/23
 * @since 1.0.0
 * https://leetcode-cn.com/problems/palindrome-number/
 */
public class IsPalindrome {
    public static void main(String[] args) {

    }
    public boolean isPalindrome(int x) {
        if(x < 0 || (x%10 == 0 && x!= 0)) return false;
        if(x == 0) return true;
        int tmp = x;
        int result  = 0;
        while (true){
            int remainder = tmp%10;
            result = result * 10 + remainder ;
            tmp = tmp/10;
            if(tmp <= result) break;
        }
        return tmp == result || result/10 == tmp;
    }
}
