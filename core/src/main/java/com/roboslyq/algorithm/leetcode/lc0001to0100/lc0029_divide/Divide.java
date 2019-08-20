package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0029_divide;

/**
 * 解题思路：
 * 1、一个J数除以N的结果K,等价于J减去K个N,即可以循环减法实现
 * 2、防止溢出，可以统一用负数表示，最后用符号标识替换为正数
 * 此题解决在Leetcode上通不过，因为会超时
 */
public class Divide {
    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE );
        Divide divide = new Divide();
        System.out.println(divide.divide(8,3));
        System.out.println(divide.divide(-8,3));
        System.out.println(divide.divide(9,3));
        System.out.println(divide.divide(32,-3));
        System.out.println(divide.divide(0,3));
        System.out.println(divide.divide(-2147483648,-1));
        System.out.println(divide.divide(-2147483648,1));
        System.out.println(divide.divide(2147483647,2));
    }
    public int divide(int dividend, int divisor) {
        if(dividend == 0){
            return 0;
        }
        if(divisor == 1){
            return dividend;
        }
        if(divisor == -1){
            if(dividend == Integer.MIN_VALUE  ){
                return Integer.MAX_VALUE;
            }else{
                return divisor * dividend;
            }
        }
        int flag = (dividend>0 && divisor>0)  || (dividend <0 && divisor <0) ? 1 : -1;
        int negativeDividend = dividend < 0? dividend : -dividend;
        int negativeDivisor = divisor < 0? divisor : -divisor;
        int subTimes = 0;
        while(negativeDividend <= negativeDivisor){
            negativeDividend = negativeDividend - negativeDivisor;
            subTimes++;
        }
        return flag == 1 ? subTimes : -subTimes;
    }
}
