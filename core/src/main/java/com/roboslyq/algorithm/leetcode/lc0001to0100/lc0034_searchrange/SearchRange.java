/**
 * Copyright (C), 2015-2019
 * FileName: SearchRange
 * Author:   luo.yongqian
 * Date:     2019/8/26 12:51
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/8/26 12:51      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0034_searchrange;

/**
 *
 * 〈使用while循环实现二分法(替代递归)〉
 * @author luo.yongqian
 * @create 2019/8/26
 * @since 1.0.0
 */
public class SearchRange {
    public static void main(String[] args) {
//        int[] test  = {5,7,7,8,8,10};
        int[] test  = {2,2};
        SearchRange searchRange = new SearchRange();
        int[] res = searchRange.searchRange(test,2);
        System.out.println(res[0] + ","+ res[1] );

    }
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (null == nums || nums.length == 0) {
            return res;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            //取中值
            int middleIndex = (right + left ) % 2 == 0 ? (right + left ) / 2 : (right + left ) / 2 + 1;
            //分支1 ： 中值 = 目标值
            if (nums[middleIndex] == target) {
                res[0] = res[1] = middleIndex; //默认只有一个值(没有重复)
                for (int i = middleIndex; i >= left; i--) {//遍列左值(检测左边是否还有重复值)
                    if (nums[i] != target) {
                        break;
                    } else {
                        res[0] = i;
                    }
                }
                for (int i = middleIndex; i <= right; i++) {//遍列左值(检测左边是否有重复值)
                    if (nums[i] != target) {
                        break;
                    } else {
                        res[1] = i;
                    }
                }
                break;//找到值后，中断循环
            }
            //分支2 ： 中值 < 目标值，表示目标值可能落在右侧，修改left为中值即可。
            if (nums[middleIndex] < target) {
                left = middleIndex + 1;
            }
            //分支3 ： 中值 > 目标值，表示目标值可能落在右侧，修改right为中值即可。
            if (nums[middleIndex] > target) {
                right = middleIndex - 1;
            }

        }
        return res;
    }

}