package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0035_searchinsert;

/**
 * 单向遍列法
 */
public class SearchInsert1 {
    public static void main(String[] args) {

    }
    public int searchInsert(int[] nums, int target) {
        int result = nums.length;
        for(int i = 0;i<nums.length;i++){
            int valueCurr = nums[i];
            if(valueCurr == target){
                result = i;
                break;
            }
        }
        return result;
    }
}

