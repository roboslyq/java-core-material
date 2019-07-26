package com.roboslyq.algorithm.leetcode.lc0007_reverse;

public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println( reverseInteger.reverse(-12345));
    }
    public int reverse(int x) {
        int flag = x < 0 ? -1 : 1;
        int tmp = Math.abs(x);
        int result  = 0;
        int tmp2 = Integer.MAX_VALUE/10;
        while (tmp > 0){
            int remainder = tmp%10;
            if(result > tmp2 ) return 0;
            result = result * 10 + remainder ;
            tmp = tmp/10;
        }
        return flag * result;
    }
}
