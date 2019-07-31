package com.roboslyq.algorithm.leetcode.lc0020_validparenthess;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IsValid1 {
    public static void main(String[] args) {
        IsValid1 isValid = new IsValid1();
        System.out.println( isValid.isValid("["));
    }
    public boolean isValid(String s) {
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        if(s == null || s.length() == 0 )  return true;
        if(s.length()%2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ;i<s.length() ; i++){
            char c = s.charAt(i);
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
