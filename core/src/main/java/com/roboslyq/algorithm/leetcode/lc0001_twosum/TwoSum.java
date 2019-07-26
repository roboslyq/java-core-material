package com.roboslyq.algorithm.leetcode.lc0001_twosum;

public class TwoSum {
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
      int[]  nums = {2, 7, 11, 15};
        System.out.println();
        int[] res = twoSum.twoSum(nums,9);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
    public int[] twoSum(int[] nums, int target) {
       // Arrays.parallelSort(nums);
        int[] result = {0,0};
           lab1: for(int i = 0;i< nums.length;i++){
            int sub= target - nums[i];
            for(int j = i +1; j<nums.length;j++){
                if(sub == nums[j]){
                result[0] = i;
                result[1] = j;
                break lab1;
                }
            }
        }
        return result;
    }
}
