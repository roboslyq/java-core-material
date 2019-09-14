/**
 * Copyright (C), 2015-2019
 * FileName: LengthOfLastWord
 * Author:   luo.yongqian
 * Date:     2019/9/12 16:58
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/12 16:58      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0058_lengthoflastword;

/**
 *
 * 〈循环遍列〉
 * @author luo.yongqian
 * @create 2019/9/12
 * @since 1.0.0
 */
public class LengthOfLastWord {
    public static void main(String[] args) {

    }
    public int lengthOfLastWord(String s) {
        if(s == null ) return 0;
        String tmp = s.trim();
        if(tmp.length() == 0) return 0;
        int len = 0;
        while (tmp.length()-1-len >= 0){
            char c = tmp.charAt(tmp.length()-1-len);
            if(c == ' '){
                break;
            }else {
                len++;
            }
        }
        return len;

    }

}