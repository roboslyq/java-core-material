package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0029_divide;

/**
 * 解题思路：
 * 1、优化Divide算法
 * 2、使用位移算法
 * 3、左移要防止溢出
 */
public class Divide1 {
    public static void main(String[] args) {
//        System.out.println(2<<1);
        System.out.println(-2<<1);
        System.out.println(3<<1);
        System.out.println(-3<<1);
//        System.out.println(1<<2);
//        System.out.println(8>>2);
//        System.out.println(Integer.MIN_VALUE );
        Divide1 divide = new Divide1();
//        System.out.println(divide.divide(8,3));
//        System.out.println(divide.divide(-8,3));
//        System.out.println(divide.divide(9,3));
//        System.out.println(divide.divide(32,-3));
//        System.out.println(divide.divide(0,3));
//        System.out.println(divide.divide(-2147483648,-1));
//        System.out.println(divide.divide(-2147483648,1));
        System.out.println(divide.divide(2147483647,2));
        System.out.println(divide.divide(-2147483648,2));
    }
    public int divide(int dividend, int divisor) {
        if(dividend == 0) return 0;//特殊情况1：被除数为0直接返回0
        if(divisor == 1) return dividend;//特殊情况2：除数为1，直接返回被除数
        if(divisor == -1){
            if(dividend == Integer.MIN_VALUE  ){ //只有这一种情况需要溢出处理：除数为-1，被除数为最小整数
                return Integer.MAX_VALUE;
            }else{
                return divisor * dividend;
            }
        }
        //结果符号（统一转换为负数处理，保证不会溢出）
        int flag = (dividend>0 && divisor>0)  || (dividend <0 && divisor <0) ? 1 : -1;
        //注意：使用正数会有溢出问题
        int negativeDividend = dividend < 0 ? dividend : -dividend;
        int negativeDivisor = divisor < 0 ? divisor : -divisor;
        /**
         * dividend绝对值大于currTimes倍的divisor时的最大值。
         * 比如 dividend  = 8，divisor = 3，那么currTimes = 2。因为 divisor*(currTimes + 1) = 9 >8
         */
        int currTimes = 1;
        int result = 0;
        //获取currTimes值
        while(negativeDividend <= negativeDivisor << 1
                && Integer.MIN_VALUE >> 1 <= negativeDivisor){//止条件防止溢出
            negativeDivisor = negativeDivisor << 1;
            currTimes =  currTimes << 1;
        }
        while(currTimes >0 && negativeDividend < 0 ){
            if(negativeDividend <= negativeDivisor){
                //减法实现除法
                negativeDividend -= negativeDivisor;
                result += currTimes;
            }
            /**
             *  1、因为如果走了条件分支negativeDividend <= negativeDivisor，那么negativeDividend ，一定会小于当前negativeDivisor
             *     所以除数及位数向右移
             *  2、如果没走分支negativeDividend <= negativeDivisor，那么更需要右移了
             */
            negativeDivisor = negativeDivisor >> 1;
            currTimes = currTimes >>1;
        }
        return flag == 1 ? result : -result;
    }
}
