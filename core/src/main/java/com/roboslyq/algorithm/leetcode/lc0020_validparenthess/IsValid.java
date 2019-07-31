package com.roboslyq.algorithm.leetcode.lc0020_validparenthess;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IsValid {
    public static void main(String[] args) {
        IsValid isValid = new IsValid();
        System.out.println( isValid.isValid("["));
    }
    public boolean isValid(String s) {
        Map<String,String> map = new HashMap<>();
        map.put(")","(");
        map.put("}","{");
        map.put("]","[");
        if(s == null || s.length() == 0 )  return true;
        if(s.length()%2 == 1) return false;
        Stack<String> stack = new Stack<>();
        for(int i = 0 ;i<s.length() ; i++){
            char c = s.charAt(i);
            if(!stack.isEmpty() && stack.peek().equals(map.get(c + ""))){
                stack.pop();
            }else{
                stack.push(c + "");
            }
        }
        return stack.isEmpty();
    }
}
