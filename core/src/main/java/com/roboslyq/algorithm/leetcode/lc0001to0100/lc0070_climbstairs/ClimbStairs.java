package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0070_climbstairs;

public class ClimbStairs {
    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        System.out.println(climbStairs.climbStairs(44));
    }

    /**
     * 循环法：f(1) = 1,f(2)=2,f(n)=f(n-1) + f(n-2)
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2)  return 2;
        int n_1 = 1;
        int n_2 = 2;
        int res = 0;
        for(int i = 3;i<=n;i++){
            res = n_2 + n_1;
            n_1 =n_2;
            n_2 = res;
        }
        return res;
    }


}

