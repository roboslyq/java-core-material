/**
 * Copyright (C), 2015-2019
 * FileName: SimplifyPath
 * Author:   luo.yongqian
 * Date:     2019/9/16 17:33
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/16 17:33      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0071_simplifypath;

import java.util.Stack;

/**
 * 〈使用栈处理〉
 *
 * @author luo.yongqian
 * @create 2019/9/16
 * @since 1.0.0
 */
public class SimplifyPath {
    public static void main(String[] args) {
//        Stack<String> stack = new Stack<>();
//        stack.push("1");
//        stack.push("2");
//        while (!stack.empty()){
//            System.out.println(stack.pop());
//        }
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath("/home//foo/"));
    }

    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {//回退到上一路径，如果栈不为空，出栈
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!item.isEmpty()
                    && !item.equals(".")//当前路径，不需要入栈，直接丢弃
            ) {
                stack.push(item);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.empty()) {
            res.insert(0, "/" + stack.pop());
        }
        return (res.length() == 0) ? "/" : res.toString();
    }
}