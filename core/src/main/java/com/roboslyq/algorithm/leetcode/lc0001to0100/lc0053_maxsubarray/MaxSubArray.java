package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0053_maxsubarray;

/**
 * 求子数组最大和
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] test = {-2,1,-3,4,-1,2,1,-5,4};
        MaxSubArray maxSubArray = new MaxSubArray();
        System.out.println(maxSubArray.maxSubArray(test));
    }
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int currentResult = 0;
        /**
         * 递归循环
         */
        for(int i = 0;i<nums.length;i++){
            currentResult = currentResult + nums[i];
            //如果当前结果大于结果，则交换
            if(currentResult > res){
                res = currentResult;
            }
            //如果currentResult小于 0，则丢弃，因为加一个小于0的数肯定更小，故丢弃
            if(currentResult < 0){
                currentResult = 0;
            }
        }
        return  res;
    }
}
