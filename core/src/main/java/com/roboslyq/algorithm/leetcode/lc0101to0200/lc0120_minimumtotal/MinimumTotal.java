/**
 * Copyright (C), 2015-2019
 * FileName: MinimumTotal
 * Author:   luo.yongqian
 * Date:     2019/9/29 9:37
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/29 9:37      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0120_minimumtotal;

import java.util.List;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/9/29
 * @since 1.0.0
 */
public class MinimumTotal {

    /**
     * 暴力穷举法
     * 1、此算法是按层来计算，首先是从上至下
     * min_val_path(n) = min(val_path(n-1) + val(n))
     * @param triangle
     * @return
     */
    int row;

    public int minimumTotal(List<List<Integer>> triangle) {
        row=triangle.size();
        return helper(0,0, triangle);
    }

    /**
     *
     * @param level 第level层
     * @param c     第level层的第c个位置
     * @param triangle 原始数据
     * @return
     */
    private int helper(int level, int c, List<List<Integer>> triangle){
        if (level==row-1){
            return triangle.get(level).get(c);
        }
        int left = helper(level+1, c, triangle);
        int right = helper(level+1, c+1, triangle);
        return Math.min(left, right) + triangle.get(level).get(c);
    }
}