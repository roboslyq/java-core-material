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
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0040_combinationsum2;

import java.util.*;

/**
 *
 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

 candidates 中的每个数字在每个组合中只能使用一次。

 说明：

 所有数字（包括目标数）都是正整数。
 解集不能包含重复的组合。 
 示例 1:

 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 所求解集为:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 示例 2:

 输入: candidates = [2,5,2,1,2], target = 5,
 所求解集为:
 [
   [1,2,2],
   [5]
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/combination-sum-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
        int [] input = {2,5,2,1,2};
        int target = 5;
        List<List<Integer>>  res =  combinationSum.combinationSum2(input,target);
        for(List<Integer> list : res){
            for(Integer tmp : list){
                System.out.print(tmp +",");
            }
            System.out.println();
        }
    }

    /**
     * 与lc0039一模一样，只需要在循环时，i+1并且去重（使用Set去重）
     */
    public Set<List<Integer>> set = new HashSet<>();//
    public Integer target = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.target = target;
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        recursive(candidates,list,0,0,true);
        return new ArrayList<>(set);
    }
    public void recursive(int[] candidates, List<Integer> currentList,int currentValue,int index,boolean firstLevel){
        if(currentValue > target){
            return;
        }
        if(currentValue == target){
            set.add(new ArrayList<>(currentList));
            return;
        }
        for(int i=index;i<candidates.length;i++){
            int currentNew = currentValue + candidates[i];
            //顶层递归时，重复元素排除
            if(firstLevel){
                if( i > 0 && candidates[i] == candidates[i - 1] ){
                    continue;
                }
            }
            currentList.add(candidates[i]);
            recursive(candidates, currentList, currentNew,i+1,false);
            currentList.remove(currentList.size() - 1);
        }
    }
}