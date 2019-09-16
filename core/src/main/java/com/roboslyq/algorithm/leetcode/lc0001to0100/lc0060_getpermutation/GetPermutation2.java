package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0060_getpermutation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯未剪枝，性能较差
 * 比较容易想到的是，使用同 「力扣」第 46 题：全排列 ，即使用回溯的思想，依次得到全排列，输出所求的第 kk 个全排列即可。但事实上，我们不必求出所有的全排列。基于以下几点考虑：
 *
 * 1、我们知道所求排列一定在叶子结点处得到。事实上，进入每一个分支的时候，我们都可以通过递归的层数，直接计算这一分支可以得到的叶子结点的个数。
 *
 * 这是因为：进入一个分支的时候，我们可以根据已经选定的数的个数，进而确定还未选定的数的个数，然后计算阶乘，就知道这一个分支的叶子结点有多少个。
 *
 * 2、如果 kk 大于这一个分支将要产生的叶子结点数，直接跳过这个分支，即“剪枝”即可。
 *
 * 这是因为：即使你回溯去做，要设置状态，回溯回来的时候状态还要重置，但其实跳过的这个分支的叶子结点具体是啥我们并不关心。
 *
 * 3、如果 kk 小于等于这一个分支将要产生的叶子结点数，那说明所求的全排列一定在这一个分支将要产生的叶子结点里，需要递归求解。
 *
 * 4、计算阶乘的时候，你可以使用循环计算，特别注意：0!=10!=1，它表示了没有数可选的时候，即表示到达叶子结点了，排列数只剩下 11 个。
 *
 * 又因为题目中说“给定 nn 的范围是 [1, 9][1,9]”，故可以实现把从 00 到 99 的阶乘计算好，放在一个数组里，可以根据索引直接获得阶乘值，见文后“代码 2”。
 *
 * 下面以示例 2：输入: n = 4n=4，k = 9k=9，介绍如何使用“回溯 + 剪枝” 的思想得到输出 "2314"。
 *
 */
public class GetPermutation2 {
    public static void main(String[] args) {

    }
    public List<Integer>  res = new ArrayList<>();
    //0到9的阶乘值（次数）
    int[] factorial = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};


    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
            used[i] = false;
        }
        List<Integer> linkedList = new LinkedList();
        for(int i = 1;i<=n;i++){
            linkedList.add(i);
        }
        getPermutation(0,linkedList);
        return res.get(k-1) +"";
    }

    public void getPermutation(int prefix ,List list){
        if(list.size() == 0){
            res.add(prefix) ;
            return;
        }
        for(int i = 0;i<list.size();i++){
            List<Integer> tmp = new LinkedList<>(list);
            getPermutation(prefix*10 + tmp.remove(i) ,tmp);
        }
    }
}
