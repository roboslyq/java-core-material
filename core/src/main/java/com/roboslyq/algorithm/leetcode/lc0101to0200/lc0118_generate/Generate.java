/**
 * Copyright (C), 2015-2019
 * FileName: Generate
 * Author:   luo.yongqian
 * Date:     2019/9/27 16:56
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/27 16:56      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0118_generate;

import com.roboslyq.algorithm.leetcode.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * 〈杨辉三角生成 〉
 * @author luo.yongqian
 * @create 2019/9/27
 * @since 1.0.0
 */
public class Generate {
    public static void main(String[] args) {
        Generate generate =  new Generate();
        List<List<Integer>>  res =  generate.generate(5);
        for(List<Integer> res1  : res){
            for(Integer res2 :  res1){
                System.out.println(res2   +",");
            }
            System.out.println();
        }
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows   ==   0) return res;
        List<Integer> num1 =  new ArrayList();
        num1.add(1);
        res.add(num1);
        if(numRows  == 1){
            return res;
        }
        for(int i = 2;i<= numRows;i++){
            Integer[] numX = new Integer[i];
            List<Integer> pre =  res.get((i-1)-1);
            numX[0] = 1;
            numX[i-1]=1;
            for(int j=1;j<=i-2;j++){
                numX[j]  =  pre.get(j-1)+pre.get(j);
            }
            res.add(new ArrayList<>(Arrays.asList(numX)));
        }
        return res;
    }
}