package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0070_climbstairs;

public class ClimbStairs1 {
    public static void main(String[] args) {
        ClimbStairs1 climbStairs1 = new ClimbStairs1();
        System.out.println(climbStairs1.climbStairs(44));
    }

    /**
     * 递归法：f(1) = 1,f(2)=2,f(n)=f(n-1) + f(n-2)
     * 代码简单，但效率慢代下
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2)  return 2;
       return climbStairs(n-1) + climbStairs(n-2);
    }


}

