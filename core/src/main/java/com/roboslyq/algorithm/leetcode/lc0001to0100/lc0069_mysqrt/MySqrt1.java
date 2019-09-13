package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0069_mysqrt;

public class MySqrt1 {
    public static void main(String[] args) {

    }

    /**
     * 二分查找法
     * 原理：当n为整数时，(1 + n/2)的平方 大于n。所以n的平方根一定在0到1+n/2之间
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        //使用long,防止中位数查找过程中溢出
        long i = 0;
        long j = x/2 +1;
        while(i < j ){
            // 取右中位值，取左中位值会死循环
            long mid = (j + i)%2 == 0 ? (j + i)/2 : (j + i)/2 + 1;
            long mid_square = mid * mid;
            if(mid_square == x){
                return (int)mid;
            }
            if(mid_square < x){//结果在右半部分
                i = mid;
            }
            else{
                j = mid -1;
            }
        }
        //简单的取整
        return (int)i;
    }
}
