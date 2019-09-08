/**
 * Copyright (C), 2015-2019
 * FileName: CombinationSum
 * Author:   luo.yongqian
 * Date:     2019/8/29 13:06
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/8/29 13:06      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0039_combinationsum;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author luo.yongqian
 * @create 2019/8/29
 * @since 1.0.0
 */

/**
 * 题解：在CombinationSum 和 CombinationSum1上性能极致优化
 * 主要是循环过程中不创建新的List，则是在结果中创建。但循环中必须删除刚添加的元素
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        CombinationSum2 combinationSum = new CombinationSum2();
        int [] input = {2,3,5};
        int target = 8;
        List<List<Integer>>  res =  combinationSum.combinationSum(input,target);
        for(List<Integer> list : res){
            for(Integer tmp : list){
                System.out.print(tmp +",");
            }
            System.out.println();
        }
    }

    //进行排序(类变量，不需要传参)
    //包含static 的leetcode会报错
//    public static List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> res = new ArrayList<>();//
    //进行排序(类变量，不需要传参)
    public Integer target = 0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //入口排序
        //Arrays.sort(candidates);
//        if(candidates[0] > target){
//            return res;
//        }
        this.target = target;
        List<Integer> list = new ArrayList<>();
        recursive(candidates,list,0,0);
        return res;
    }

    /**
     *
     * @param candidates 原数组
     * @param currentList 当前list
     * @param currentValue 当前值，不需要当前List计算
     * @param index 当前索引位轩，默认从0开始
     */
    public void recursive(int[] candidates, List<Integer> currentList,int currentValue,int index){
        if(currentValue > target){
            return;
        }
        if(currentValue == target){
            res.add(new ArrayList<>(currentList));
            return;
        }
        for(int i=index;i<candidates.length;i++){
            int currentNew = currentValue + candidates[i];
            currentList.add(candidates[i]);
            //递归回溯(注意：i不变，保证下次再包含当前位置元素）
            recursive(candidates, currentList, currentNew,i);
            currentList.remove(currentList.size() - 1);
        }
    }
}