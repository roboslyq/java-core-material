package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0041_firstmissingpositive;

import java.util.Arrays;

/**
 * 利用Arrays数组排序，但排序的复杂度为O(nlogn)
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] test = {3,4,-1,1};
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        System.out.println(firstMissingPositive.firstMissingPositive(test));
    }
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int res = 1;
        for(int i = 0; i<nums.length ;i++){
            if(nums[i] == res ){
                res++;
                continue;
            }
            if(nums[i] > res){
               break;
            }
            if(nums[i] < res){
                if(i == nums.length - 1){
                    res = nums[i] +1;
                    break;
                }
            }
        }
        return res >0 ? res : 1;
    }
}
