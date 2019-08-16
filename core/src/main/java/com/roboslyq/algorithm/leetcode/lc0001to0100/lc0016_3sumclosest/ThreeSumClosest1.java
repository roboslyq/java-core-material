package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0016_3sumclosest;

import java.util.Arrays;

/**
 *
 * 复用0015题排序后，双指针的思想完成相关求解
 */
public class ThreeSumClosest1 {
    public static void main(String[] args) {
        ThreeSumClosest1 threeSumClosest = new ThreeSumClosest1();
        int res = threeSumClosest.threeSumClosest(new int[]{-3,0,1,2},1);
            System.out.println(res);
    }
    public int threeSumClosest(int[] nums, int target) {
        int result = 0;
        Arrays.sort(nums);
        //特殊情况处理
        if (nums.length < 3) {
            return 0;
        }
        //如果nums[currentFirstIndex] 大于 0，表示循环结束
        for (int index = 0; index < nums.length-1; index++) {
            int current = nums[index];
            //i后面的数组nums[i+1] 和 nums[length]重新做为一个数组，分别取左右值
            int leftIndex = index + 1;
            int rightIndex = nums.length - 1;
            while (leftIndex < rightIndex) {
                int left = nums[leftIndex];
                int right = nums[rightIndex];
                int res = current + left + right;
                int sub = res - target;
                if((index == 0 &&  leftIndex == 1 && rightIndex== nums.length - 1) ||
                        (Math.abs(sub ) < Math.abs(result - target))
                ){
                    result = res;
                }
                if(sub == 0){//相等直接返回结果
                    return res;
                }else if(sub >0){
                    rightIndex--;
                }else{
                    leftIndex++;
                }
            }
        }
       return result;
    }
}
