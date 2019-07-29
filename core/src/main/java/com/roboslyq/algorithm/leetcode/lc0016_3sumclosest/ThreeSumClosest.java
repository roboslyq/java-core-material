package com.roboslyq.algorithm.leetcode.lc0016_3sumclosest;

import java.util.Arrays;

/**
 * 暴力法，可以借助lc0015双指针法，逐渐靠近目标函数
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        int res = threeSumClosest.threeSumClosest(new int[]{-1,2,1,-4},1);
            System.out.println(res);
    }
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 0;
        for(int i = 0 ;i< nums.length -2 ;i++){
            int vali = nums[i];
            for(int j = i+1;j<nums.length-1;j++){
                int next1 = nums[j];
                for(int k = j + 1;k < nums.length ;k++){
                    int next2 = nums[k];
                    int resTmp = vali + next1 + next2;
                    if(resTmp == target ){
                        return target;
                    }else{
                        if(i == 0 && j==1 && k==2) {
                            result = resTmp ;
                        }
                        else{
                            if (Math.abs(resTmp -target ) < Math.abs(result - target) ){
                                result = resTmp;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
