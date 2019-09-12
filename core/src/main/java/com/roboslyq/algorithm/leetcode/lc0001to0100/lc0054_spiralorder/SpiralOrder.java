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
 * 〈〉
 * @author luo.yongqian
 * @create 2019/9/12
 * @since 1.0.0
 */
public class SpiralOrder {
    public static void main(String[] args) {
        int[][] test = {
            {1,2,3,4},
            {4,5,6,8},
            {7,8,9,10}
        };
        SpiralOrder spiralOrder = new SpiralOrder();
        List<Integer> integers = spiralOrder.spiralOrder(test);
        integers.stream().forEach(System.out::println);
    }
    public List<Integer> spiralOrder(int[][] matrix) {

        if(matrix == null || matrix.length == 0){
            return  new ArrayList<>();
        }
        int y = matrix.length;
        int x = matrix[0].length;
        List<Integer> res = new ArrayList<>(x*y);
        int count = x*y;
        int c_h = 0;
        int c_d = y-1;
        int c_r = x -1;
        int c_l = 0;
        int cur_x = 0;//x轴值（行数）
        int cur_y = 0;//y轴值（列数）
        int for_type = 0; // 0 : 行右，1：上下 2 行左 3 下上

        while (count-- >0){
            res.add(matrix[cur_x][cur_y]);
            switch (for_type){
                case 0:
                    cur_y++;
                    if(cur_y == c_r){
                        c_h++;
                        for_type=1;
                    };
                    break;
                case 1:
                    cur_x++;
                    if(cur_x == c_d){
                        c_r--;
                        for_type=2;
                    }
                    break;
                case 2:
                    cur_y--;
                    if(cur_y == c_l){
                        c_d--;
                        for_type=3;
                    }
                    break;
                case 3:
                    cur_x--;
                    if(cur_x == c_h){
                        c_l++;
                        for_type = 0;
                    }
                    break;
            }
        }
        return res;

    }

}