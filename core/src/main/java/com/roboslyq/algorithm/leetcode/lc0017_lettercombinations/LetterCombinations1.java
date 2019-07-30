/**
 * Copyright (C), 2015-2019
 * FileName: LetterCombinations
 * Author:   luo.yongqian
 * Date:     2019/7/30 18:07
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/7/30 18:07      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0017_lettercombinations;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/7/30
 * @since 1.0.0
 * 队列思想
 */
public class LetterCombinations1 {
    public static void main(String[] args) {
        LetterCombinations1 letterCombinations = new LetterCombinations1();
        List<String> res = letterCombinations.letterCombinations("234");
        System.out.println("res.size()"+res.size());
        for(String str : res){
            System.out.println(str);
        }
    }
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }
}