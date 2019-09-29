/**
 * Copyright (C), 2015-2019
 * FileName: EvalRPN
 * Author:   luo.yongqian
 * Date:     2019/9/29 11:37
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/29 11:37      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0150_evalrpn;

import java.util.Stack;

/**
 *
 * 〈根据逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 *
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。〉
 * @author luo.yongqian
 * @create 2019/9/29
 * @since 1.0.0
 */
public class EvalRPN {
    public static void main(String[] args) {
        EvalRPN evalRPN = new EvalRPN();
//        System.out.println(evalRPN.cal(3,5,"+"));
        String[] test = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN.evalRPN(test));
    }

    /**
     * 使用栈，当遇到运算符时*+-/,出栈两个数，计算然后得到结果并入栈即可。
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if(tokens.length == 0) return 0;
        if(tokens.length == 1) return Integer.valueOf(tokens[0]);
       Stack<Integer> stack = new Stack<>();
        for (String strI : tokens) {
            if (strI.equals("+") || strI.equals("-") || strI.equals("*") || strI.equals("/")) {
                int val2 = stack.pop();
                int val1 = stack.pop();
                stack.push(cal(val1, val2, strI));
            } else {
                stack.push(Integer.valueOf(strI));
            }
        }
        return stack.pop();

    }
    public int cal(int val1,int val2,String cal) {
        if (cal.equals("+")) return val1 + val2;
        if (cal.equals("-")) return val1 - val2;
        if (cal.equals("*")) return val1 * val2;
        if (cal.equals("/")) return val1 / val2;
        return 0;
    }
}