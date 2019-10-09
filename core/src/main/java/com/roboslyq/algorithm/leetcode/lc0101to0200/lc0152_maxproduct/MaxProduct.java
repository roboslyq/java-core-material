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
     * 最优解动态规则，先找出动态方程：
     * Max(i) = Max(Max(i-1),Max(i-1)*i)
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int curMax = Integer.MIN_VALUE;
        int curIndex = 0;
        while (nums[curIndex] == 0){
            curIndex++;
            if(curIndex >= nums.length) return 0;
        }
        int curMutilVal = nums[curIndex];
        for (int i = curIndex+1; i < nums.length ; i++) {
            if(nums[i] == 0){//遇到0截断处理，重新开始计算值
                curMax = Math.max(curMutilVal,curMax);
                curMutilVal = 0;
            }else if(nums[i] < 0){//遇到负数
                if(needMutilNegative(nums,i+1)){
                    if(curMutilVal == 0){
                        curMutilVal =  nums[i];
                    }else{
                        curMutilVal = curMutilVal * nums[i];
                    }
                }else {
                    curMax = Math.max(curMutilVal,curMax);
                    curMutilVal = 0;
                }
            }else {
                if(curMutilVal == 0){
                    curMutilVal =  nums[i];
                }else{
                    curMutilVal = curMutilVal * nums[i];
                }
            }
        }
        return Math.max(curMax,curMutilVal);
    }

    public boolean needMutilNegative(int[] nums,int nextIndex){
        for (int i = nextIndex; i < nums.length; i++) {
            if(nums[i] < 0) return true;
            if(nums[i] == 0) return false;
        }
        return false;
    }

}