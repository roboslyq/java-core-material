package com.roboslyq.algorithm.leetcode.lc0015_threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * *  *  *  -4,-1,-1, 0, 1, 2,
 */

public class ThreeSum1 {
    public static void main(String[] args) {
        ThreeSum1 threeSum = new ThreeSum1();
        List<List<Integer>> res = threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (List<Integer> listInt : res) {
            for (Integer integer : listInt) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        //特殊情况处理
        if (nums[0] > 0 || nums[nums.length - 1] < 0 || nums.length < 3) {
            return null;
        }
        //如果nums[currentFirstIndex] 大于 0，表示循环结束
        for (int index = 0; index < nums.length; index++) {
            int current = nums[index];
            if (current > 0) {
                break;
            }
            //i后面的数组nums[i+1] 和 nums[length]重新做为一个数组，分别取左右值
            int leftIndex = index + 1;
            int rightIndex = nums.length - 1;
            int left = nums[leftIndex];
            int right = nums[rightIndex];
            if (left == nums[leftIndex - 1]) {
                continue;
            }

            while (leftIndex < rightIndex) {
                int res = current + left + right;
                if (res == 0) {
                    resultList.add(Arrays.asList(current, left, right));
                    //去重
                    while (leftIndex < rightIndex && nums[leftIndex + 1] == left) leftIndex++;
                    while (leftIndex < rightIndex && nums[rightIndex - 1] == right) rightIndex--;
                    //移动指针到下一个元素开始
                    leftIndex++;
                    rightIndex--;

                } else if (res < 0) leftIndex++;
                else rightIndex--;
            }
        }
        return resultList;

    }
}
