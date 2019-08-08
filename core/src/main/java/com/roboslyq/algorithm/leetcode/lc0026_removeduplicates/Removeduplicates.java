/**
 * Copyright (C), 2015-2019
 * FileName: Removeduplicates
 * Author:   luo.yongqian
 * Date:     2019/8/8 12:57
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/8/8 12:57      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0026_removeduplicates;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/8/8
 * @since 1.0.0
 */
public class Removeduplicates {
    public static void main(String[] args) {
        Removeduplicates removeduplicates = new Removeduplicates();
        int[] nums = new int[]{1, 1, 2,3,4,4,4,5};
        System.out.println(removeduplicates.removeDuplicates(nums));
    }
    public int removeDuplicates(int[] nums) {
        //默认第一个元素不相同，所以res初始值为1
        int res = 1;
        if(nums != null && nums.length>0){
            //当前索引
            int currentIndex = 0;
            //当前索引值
            int currentValue = nums[currentIndex];
            for(int i=1;i<nums.length;i++){
                if(currentValue != nums[i]){//不相等时，更新当前索引及下一索引的值
                    res++;
                    currentValue=nums[i];
                    nums[++currentIndex] = nums[i];
                }
            }
        }

        return res;
    }

}