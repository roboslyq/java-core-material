/**
 * Copyright (C), 2015-2019
 * FileName: SpiralOrder
 * Author:   luo.yongqian
 * Date:     2019/9/12 17:07
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/12 17:07      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0054_spiralorder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 〈给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 *
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author luo.yongqian
 * @create 2019/9/12
 * @since 1.0.0
 */
public class SpiralOrder1 {
    public static void main(String[] args) {
//        int[][] test = {
//            {1,2,3,4},
//            {10,11,12,5},
//            {9,8,7,6}
//        };
        int[][] test = {
            {1 },
            {4 },
            {7}
        };
        SpiralOrder1 spiralOrder = new SpiralOrder1();
        List<Integer> integers = spiralOrder.spiralOrder(test);
        integers.stream().forEach(System.out::println);
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        //处理数组为空的情况
        if(matrix == null || matrix.length == 0){
            return  new ArrayList<>();
        }
        int x = matrix.length;
        int y = matrix[0].length;
        List<Integer> res = new ArrayList<>(x*y);
        int count = x * y;
        // i表示行数，j表示列数（位置标识）
        int cur_min_i = 0; //行双指针，当前上行位置
        int cur_max_i = x-1;//行双指针，当前下行位置
        int cur_max_j = y -1;//双指针，当前最右列位置
        int cur_min_j = 0; //列又指针，当前最左行位置
        //当前元素所在点位
        int cur_i = 0;//x轴值（行数）
        int cur_j = 0;//y轴值（列数）
        //当前移动类型（一共可分解为4种移动类型）
        int for_type = 0; // 0 : 行从左向右，1：列从上往下 2 :行从右向左 3 :列从下往上
        while (count-- > 0){
            res.add(matrix[cur_i][cur_j]);//添加当前元素到结果表中
            switch (for_type){
                case 0:
                    if(cur_j == cur_max_j){
                        cur_min_i++;
                        cur_i++;
                        for_type=1;
                    }else{
                        cur_j++;
                    }
                    break;
                case 1:
                    if(cur_i == cur_max_i){
                        cur_max_j--;
                        cur_j--;
                        for_type=2;
                    }else{
                        cur_i++;
                    }
                    break;
                case 2:
                    if(cur_j == cur_min_j){
                        cur_max_i--;
                        cur_i--;
                        for_type=3;
                    }else{
                        cur_j--;
                    }
                    break;
                case 3:
                    if(cur_i == cur_min_i){
                        cur_min_j++;
                        cur_j++;
                        for_type = 0;
                    }else {
                        cur_i--;
                    }
                    break;
            }
        }
        return res;
    }
}