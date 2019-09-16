package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0047_permuteunique;

import java.util.*;

/**
 * 回溯法1：
 * 1、没有使用数组标识位，而是使用List.remove(i))
 * 2、没有剪枝支重，而是使用结果Set去重，因此对性能有一定的影响
 */
public class PermuteUnique {
    public static void main(String[] args) {
        PermuteUnique permuteUnique = new PermuteUnique();
        List<List<Integer>> res = permuteUnique.permuteUnique(new int[]{1,1,3});
        for(List<Integer> list : res){
            list.stream().forEach(System.out::print);
            System.out.println();
        }
    }
    Set<List<Integer>> resTmp = new HashSet<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return  new ArrayList<>();
        }

        ArrayList numsList = new ArrayList<>(nums.length);
        for(int i= 0;i<nums.length;i++){
            numsList.add(nums[i]);
        }
        permute(numsList,new ArrayList<>() );
        return new ArrayList<>(resTmp);
    }
    public void permute(List<Integer> nums,ArrayList<Integer> prefix){
        if(nums.size() == 0){
            resTmp.add(new ArrayList<>(prefix));
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
