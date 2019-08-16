/**
 * Copyright (C), 2015-2019
 * FileName: LongestPalindrome1
 * Author:   luo.yongqian
 * Date:     2019/7/11 16:48
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/7/11 16:48      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0004_median2sortedarray;

import java.util.Arrays;

/**
 *
 * 〈常规解法，合并排序，性能可能不太好〉
 * @author luo.yongqian
 * @create 2019/7/11
 * @since 1.0.0
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class Median2SortedArray {
    public static void main(String[] args) {

    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums=new int[nums1.length+nums2.length];
        System.arraycopy(nums1, 0, nums, 0, nums1.length);
        System.arraycopy(nums2, 0, nums, nums1.length, nums2.length);
        Arrays.sort(nums);
        int a=nums.length%2,b=nums.length/2;
        double num=0;
        if(a!=0){
            num= nums[b];
        }else {
            num= (nums[b]+ nums[b-1])*0.5;
        }
        return num;
    }
}