package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0047_permuteunique;

import java.util.*;

/**
 * 回溯法2（在1的基础上进行相关优化）：
 * 1、没有使用数组标识位，而是使用List.remove(i))
 * 2、不使用Set去重，而是进行剪枝处理
 */
public class PermuteUnique1 {
    public static void main(String[] args) {
        PermuteUnique1 permuteUnique = new PermuteUnique1();
        List<List<Integer>> res = permuteUnique.permuteUnique(new int[]{1,1,3});
        for(List<Integer> list : res){
            list.stream().forEach(System.out::print);
            System.out.println();
        }
    }
    ArrayList<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return  new ArrayList<>();
        }
        //对nums进行排序，从而方便去重
        Arrays.sort(nums);
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
            // 修改 2：因为排序以后重复的数一定不会出现在开始，故 i > 0
            // 和之前的数相等，并且之前的数还未使用过，只有出现这种情况，才会出现相同分支
            // 这种情况跳过即可
            if (i > 0 && nums.get(i) == nums.get(i-1)) {
                continue;
            }
            ArrayList<Integer> tmp = new ArrayList<>(nums);
            prefix.add(tmp.remove(i));
            permute(tmp,prefix);
            prefix.remove(prefix.size()-1);
        }
    }
}
