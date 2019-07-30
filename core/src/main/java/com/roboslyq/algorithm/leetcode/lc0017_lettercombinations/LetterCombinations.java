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
 * 难点是如果保存数据
 */
public class LetterCombinations {
    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> res = letterCombinations.letterCombinations("234");
        System.out.println("res.size()"+res.size());
        for(String str : res){
            System.out.println(str);
        }
    }
    public List<String> letterCombinations(String digits) {
         String[] mapNum = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> resultCurrent = new LinkedList<>();
        List<String> resultPutInto  = new LinkedList<>();
        for(int i = 0;i< digits.length();i++){
            if(i>1){
                resultCurrent.clear();//释放空间
                resultCurrent = resultPutInto;
                resultPutInto = new LinkedList<>();
;            }
            int index = Integer.valueOf(digits.charAt(i)+"");
            String currentStr = mapNum[index];
            if(resultCurrent.size() == 0){
                for(int j=0;j<currentStr.length();j++){
                    resultCurrent.add(currentStr.charAt(j)+"");
                }
            }else{
                for(String resTmp : resultCurrent){
                    for(int j=0;j<currentStr.length();j++){
                        resultPutInto.add(resTmp + currentStr.charAt(j));
                    }
                }
            }
        }
        return  resultPutInto.size() > resultCurrent.size() ? resultPutInto : resultCurrent;
    }
}