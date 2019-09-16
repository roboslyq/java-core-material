package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0046_permute;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯法1：没有使用数组标识位，而是使用List.remove(i))
 */
public class Permute {
    public static void main(String[] args) {
        Permute permute = new Permute();
        permute.permute(new int[]{1,2,3});
        for(List<Integer> list : permute.res){
            list.stream().forEach(System.out::print);
            System.out.println();
        }
    }
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }

        ArrayList numsList = new ArrayList<>(nums.length);
        for(int i= 0;i<nums.length;i++){
            numsList.add(nums[i]);
        }
        permute(numsList,new ArrayList<>() );
        return res;
    }
    public void permute(List<Integer> nums,ArrayList<Integer> prefix){
        if(nums.size() == 0){
            res.add(new ArrayList<>(prefix));
            return;
        }
        for(int i=0;i<nums.size();i++){
            ArrayList<Integer> tmp = new ArrayList<>(nums);
            prefix.add(tmp.remove(i));
            permute(tmp,prefix);
            prefix.remove(prefix.size()-1);
        }
    }
}
