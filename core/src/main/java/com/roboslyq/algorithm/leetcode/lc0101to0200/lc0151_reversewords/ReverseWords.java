/**
 * Copyright (C), 2015-2019
 * FileName: ReverseWords
 * Author:   luo.yongqian
 * Date:     2019/10/9 12:33
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/10/9 12:33      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0151_reversewords;

/**
 *
 * 〈给定一个字符串，逐个翻转字符串中的每个单词。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。〉
 * @author luo.yongqian
 * @create 2019/10/9
 * @since 1.0.0
 */
public class ReverseWords {
    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        System.out.println(reverseWords.reverseWords("the sky is blue"));
        System.out.println(reverseWords.reverseWords("   hello   world!   "));
    }

    /**
     * 借用了Java中的String.split（）方法，此方法可以自己实现
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String temp = s.trim();
        if(temp.length() == 0) return temp;
        String[] array = s.split(" ");
        StringBuilder res = new StringBuilder();
        for(int j = array.length-1;j>=0;j--){
            if(array[j].trim().length() > 0){
                res.append(array[j]);
                res.append(" ");
            }
        }
        return res.toString().trim();
    }
}