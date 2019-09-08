package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0041_firstmissingpositive;

import java.util.Arrays;

/**
 *  使用BitMap思想
 *  1、1如果不在，则缺失值为1
 *  2、将小于0，大于数组长度 的值替换为1（因为缺失的整数一定在1~n+1之间，n为数组的长度）
 */
public class FirstMissingPositive1 {
    public static void main(String[] args) {
//        int[] test = {7,8,9,11,12};
//        int[] test1 = { 3,4,-1,1};
        int[] test2 = { 1};

        FirstMissingPositive1 firstMissingPositive = new FirstMissingPositive1();
//        System.out.println(firstMissingPositive.firstMissingPositive(test));
//        System.out.println(firstMissingPositive.firstMissingPositive(test1));
        System.out.println(firstMissingPositive.firstMissingPositive(test2));
    }
    public int firstMissingPositive(int[] nums) {
        //处理特殊情况1：为空或者长度为0，直接返回1
        if(nums == null || nums.length == 0){
            return 1;
        }
        int res = 0;
        //初始化bitmap
        Integer[] bit = new Integer[nums.length];
        for(int i = 0;i<bit.length;i++){
            bit[i] = 0;
        }
        //将结果填充到bitMap中
        for(int i= 0 ;i<nums.length;i++){
            int numI = nums[i];
            if(numI > 0 && numI <= nums.length){
                bit[numI-1] = bit[numI-1] + 1;
            }
        }
        for(int i = 0;i<bit.length;i++){
            //判断bitmap
            if(bit[i] == 0){
                res = i+1;
                break;
            }
            //如果所有的bitmap中没有缺失数据，即遍列到最后也没有是生break中断，即缺失的数据为bit.length + 1
            if(i == bit.length -1){
                res = bit.length +1;
            }
        }
        return res == 0 ? nums.length : res;
    }
}
