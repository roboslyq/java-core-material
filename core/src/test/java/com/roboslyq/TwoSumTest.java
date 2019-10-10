package com.roboslyq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSumTest {
    public static void main(String[] args) {
        TwoSumTest twoSum = new TwoSumTest();
        int[]  nums = {2, 2,7, 11, 15,3,6};
        System.out.println();
        List<List<Integer>> res= twoSum.twoSum(nums,9);
        for(List<Integer> tmp : res){
            for(Integer tmp1 :tmp){
                System.out.print(tmp1+",");
            }
            System.out.println();
        }
    }
    public List<List<Integer>> twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0;i< nums.length;i++){
            int sub= target - nums[i];
            if(i>0 && nums[i - 1] == nums[i] ) continue;
            for(int j = i +1; j<nums.length;j++){
                if(sub == nums[j]){
                    List<Integer> resTmp = new ArrayList<>();
                    resTmp.add(nums[i]);
                    resTmp.add(nums[j]);
                    res.add(resTmp);
                }
            }
        }
        return res;
    }
}
