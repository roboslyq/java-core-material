package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0069_mysqrt;

public class MySqrt {
    public static void main(String[] args) {

    }
    public int mySqrt(int x) {
        //取巧算法^_^,此算法来自于 Math.sqrt(double a)
        Double tmp = StrictMath.sqrt(x);
        return tmp.intValue();
    }

    /**
     * 二分查找法
     * 原理：当n为整数时，(1 + n/2)的平方 大于n。所以n的平方根一定在0到1+n/2之间
     * @param x
     * @return
     */
    public int mySqrt1(int x) {

        int i = 0;
        int j = x/2 +1;
        while(i <=j ){
                //取右中位数
                int mid = (j + i)%2 == 0 ? (j + i)/2 : (j + i)/2 + 1;
                int mid_square = mid * mid;
                if(mid_square == x){
                    return mid;
                }
                if(mid_square < x){//在右半部分
                    i = mid;
                }
                else{
                    j = mid -1;
                }
        }
        //
        return i;
    }
}
