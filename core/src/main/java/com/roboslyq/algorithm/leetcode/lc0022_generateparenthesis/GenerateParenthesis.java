/**
 * Copyright (C), 2015-2019
 * FileName: GenerateParenthesis
 * Author:   luo.yongqian
 * Date:     2019/8/5 13:04
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/8/5 13:04      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0022_generateparenthesis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 〈为了枚举某些内容，我们通常希望将其表示为更容易计算的不相交子集的总和。〉
 * @author luo.yongqian
 * @create 2019/8/5
 * @since 1.0.0
 */
public class GenerateParenthesis {
    public static Integer index = 0;
    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        List<String> res = generateParenthesis.generateParenthesis(2);
        for(String str : res){
            System.out.println(str);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        System.out.println((++index) +":"+new String(current));
        System.out.println();
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }

}