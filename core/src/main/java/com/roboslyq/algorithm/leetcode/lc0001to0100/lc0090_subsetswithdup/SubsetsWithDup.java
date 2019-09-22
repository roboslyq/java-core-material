package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0090_subsetswithdup;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯法（递归法）
 */
public class SubsetsWithDup {
    public List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums.length == 0) return res;
        Arrays.sort(nums);//排序
        //这种递归通常有4个条件：结果集合，临时集体，当前数组，当前数组位置。其中，变化的
        //只有当前数组位置和临时集合。结果集合和当前数组相对不变。
        //将结果集合放到类变量也可以
        subsetsWithDupRecursion(nums,0,new LinkedList<>());
        return res;
    }

    public void subsetsWithDupRecursion(int[] nums,int currentIndex,LinkedList<Integer> tmpRes){
        res.add(new LinkedList<>(tmpRes)); //添加当前临时集合
        //遍列临时集合
        for(int i = currentIndex; i< nums.length; i++){
            // i>currentIndex 表示不是第一个循环，并且nums[i-1] == nums[i]表示当前元素重复，则可以跳过去重
            if(i > currentIndex && nums[i-1] == nums[i]){
                continue;
            }
            //添加元素
            tmpRes.add(nums[i]);
            //递归调用
            subsetsWithDupRecursion(nums, i+1,tmpRes);
            //删除元素，for循环下一次可以重复利用此集合
            tmpRes.removeLast();
        }
    }
}
