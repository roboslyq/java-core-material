package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0047_permuteunique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回溯法3（在PermuteUnique1的基础上进行相关优化）：
 * 1、使用数组标识位，而不是使用List.remove(i))
 * 2、不使用Set去重，而是进行剪枝处理
 */
public class PermuteUnique2 {
    public static void main(String[] args) {
        PermuteUnique2 permuteUnique = new PermuteUnique2();
        List<List<Integer>> res = permuteUnique.permuteUnique(new int[]{1,1,3});
        for(List<Integer> list : res){
            list.stream().forEach(System.out::print);
            System.out.println();
        }
    }
    ArrayList<List<Integer>> res = new ArrayList<>();
    boolean[] used ;
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return  new ArrayList<>();
        }
        //对nums进行排序，从而方便去重
        Arrays.sort(nums);
        used = new boolean[nums.length];
        Arrays.fill(used,false);

        permute(nums,new ArrayList<>() );
        return res;
    }
    public void permute(int[] nums,ArrayList<Integer> prefix){
        if(prefix.size() == nums.length){
            res.add(new ArrayList<>(prefix));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if (!used[i]) {
                // 修改 2：因为排序以后重复的数一定不会出现在开始，故 i > 0
                // 和之前的数相等，并且之前的数还未使用过，只有出现这种情况，才会出现相同分支
                // 这种情况跳过即可。注意：!used[i-1]，如果为true，表示没有使用，在当前层重复。如果元素已经使用，表示元素不在当前层
                if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
                    continue;
                }
                used[i] = true;
                prefix.add(nums[i]);
                permute(nums,prefix);
                prefix.remove(prefix.size()-1);
                used[i] = false;
            }
        }
    }
}
