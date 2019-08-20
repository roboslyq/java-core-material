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
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0022_generateparenthesis;

import java.util.*;

/**
 *
 * 〈为了枚举某些内容，我们通常希望将其表示为更容易计算的不相交子集的总和。〉
 * @author luo.yongqian
 * @create 2019/8/5
 * @since 1.0.0
 */
public class GenerateParenthesis1 {
    public static Integer totalLength = 0;
    static Map<Character,Character> map = new HashMap<>();
    static {
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
    }

    public static void main(String[] args) {
        GenerateParenthesis1 generateParenthesis = new GenerateParenthesis1();
        List<String> res = generateParenthesis.generateParenthesis(3);
        for(String str : res){
            System.out.println(str);
        }
    }
    public List<String> generateParenthesis(int n) {
        totalLength = 2*n;
        char[] curr = new char[n*2];
        //结果保存域
        List<String> res = new ArrayList<>();
        doGenerate(curr,0,res);
        return res;
    }
    public void doGenerate(char[] curr,int currIndex,List<String> res){
        if(currIndex == totalLength){
            if(isValid(curr)){
                res.add(new String(curr));
            }
//            res.add(new String(curr));
        }else {
            curr[currIndex] = '(';
            doGenerate(curr,currIndex+1,res);
            curr[currIndex] = ')';
            doGenerate(curr,currIndex+1,res);
        }
    }

    /**
     * 复用20题检查结果串是否合法
     * @param s
     * @return
     */
    public boolean isValid(char[] s) {
        if(s == null || s.length == 0 )  return true;
        if(s.length%2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ;i<s.length ; i++){
            char c = s[i];
            if(map.containsKey(c)){
                if(!stack.isEmpty() && stack.peek().equals(map.get(c))){
                    stack.pop();
                }else{
                    return false;
                }
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

}