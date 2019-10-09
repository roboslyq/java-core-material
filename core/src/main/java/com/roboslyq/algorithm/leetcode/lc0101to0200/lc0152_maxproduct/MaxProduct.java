/**
 * Copyright (C), 2015-2019
 * FileName: MaxProduct
 * Author:   luo.yongqian
 * Date:     2019/10/9 12:45
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/10/9 12:45      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0152_maxproduct;

/**
 *
 * 〈给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。〉
 * @author luo.yongqian
 * @create 2019/10/9
 * @since 1.0.0
 */
public class MaxProduct {
    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        System.out.println(maxProduct.maxProduct(new int[]{-4,-3}));
        System.out.println(maxProduct.maxProduct(new int[]{-2,0,-1}));
    }
     /**
     * 解题思路（动态规划):
     * <p>
     * 求积的最大值，最麻烦的就是
     * 当遇到0的时候，整个乘积会变成0；当遇到负数的时候，当前的最大乘积会变成最小乘积，最小乘积会变成最大乘积
     * <p>
     * 所以用两个变量进行保存之前的最大值(preMax)和最小值(preMin)
     * <p>
     * 当前的最大值等有三种可能：最大值(之前最大值)、之前最小值和当前值的乘积，当前值()，这三个数的最大值。
     * 当前的最小值等于已知的最大值、最小值和当前值的乘积，当前值，这三个数的最小值。
     * 结果是最大值数组中的最大值。
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int preMax = nums[0];
        int preMin = nums[0];
        int res = nums[0];
        int curMax = 0;
        int curMin = 0;
        for (int i = 1; i < nums.length ; i++) {
            /**
             * 获取当前的最大值：Math.max(nums[i] * preMax,nums[i] * preMin)
             * 获取当前的最小值：Math.min(nums[i] * preMax,nums[i] * preMin)
             *
             */
            curMax = Math.max(nums[i],Math.max(nums[i] * preMax,nums[i] * preMin));
            curMin = Math.min(nums[i],Math.min(nums[i] * preMax,nums[i] * preMin));
            preMax = Math.max(curMax,curMin);
            preMin = Math.min(curMax,curMin);
            res = Math.max(res, curMax);
        }
        return res;
    }

}