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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/7/30
 * @since 1.0.0
 * 递归思想
 */
public class LetterCombinations2 {
    public static void main(String[] args) {
        LetterCombinations2 letterCombinations = new LetterCombinations2();
        List<String> res = letterCombinations.letterCombinations("234");
        System.out.println("res.size()"+res.size());
        for(String str : res){
            System.out.println(str);
        }
    }
  private String letterMap[] = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    private ArrayList<String> res;

    public List<String> letterCombinations(String digits) {
        //空字符串输入直接返回
        if(digits.equals(""))  return res;
        res = new ArrayList<String>();
        //index为0开始递归，初始串为“”
        findCombination(digits, 0, "");
        return res;
    }

    private void findCombination(String digits, int index, String s){
        if(index == digits.length()){
            res.add(s);
            return;
        }
        Character c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for(int i = 0 ; i < letters.length() ; i ++){
            findCombination(digits, index+1, s + letters.charAt(i));
        }

        return;
    }
}