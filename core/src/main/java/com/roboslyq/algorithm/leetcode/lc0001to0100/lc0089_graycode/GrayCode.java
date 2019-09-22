package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0089_graycode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 按照动态规划或者说递归的思路去想，也就是解决了小问题，怎么解决大问题。
 *
 * 我们假设我们有了 n = 2 的解，然后考虑怎么得到 n = 3 的解。
 *
 * n = 2 的解
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 如果再增加一位，无非是在最高位增加 0 或者 1，考虑先增加 0。由于加的是 0，其实数值并没有变化。
 *
 * n = 3 的解，最高位是 0
 * 000 - 0
 * 010 - 2
 * 011 - 3
 * 001 - 1
 *
 * 再考虑增加 1，在 n = 2 的解基础上在最高位把 1 丢过去？
 *
 * n = 3 的解
 * 000 - 0
 * 010 - 2
 * 011 - 3
 * 001 - 1
 * ------------- 下面的是新增的
 * 100 - 4
 * 110 - 6
 * 111 - 7
 * 101 - 5
 * 似乎没这么简单哈哈，第 4 行 001 和新增的第 5 行 100，有 2 个 bit 位不同了，当然不可以了。怎么解决呢？
 *
 * 很简单，第 5 行新增的数据最高位由之前的第 4 行的 0 变成了 1，所以其它位就不要变化了，直接把第 4 行的其它位拉过来，也就是 101。
 *
 * 接下来，为了使得第 6 行和第 5 行只有一位不同，由于第 5 行拉的第 4 行的低位，而第 4 行和第 3 行只有一位不同。
 * 所以第 6 行可以把第 3 行的低位拿过来,其他行同理，
 * 这样拿低位，相当于在当前的数的基础上前面添加了1，即2^n-1次方，也就是 1 << ( n - 1) 。所以我们的算法可以用迭代求出来了。
 *
 * 所以如果知道了 n = 2 的解的话，如果是 { 0, 1, 3, 2}，那么 n = 3 的解就是 { 0, 1, 3, 2, 2 + 4, 3 + 4, 1 + 4, 0 + 4 }，即 { 0 1 3 2 6 7 5 4 }。
 * 之前的解直接照搬过来，然后倒序把每个数加上 1 << ( n - 1) 添加到结果中即可。
 *
 */

public class GrayCode {
    public static void main(String[] args) {

    }
    public List<Integer> grayCode(int n) {
        List<Integer> res = new LinkedList<>();
        res.add(0);
        for(int i = 0;i<n;i++){
            int add = 1<< i;//需要添加的数
            //倒序遍历，并且加上一个值添加到结果中
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(res.get(j) + add);
            }
        }
        return res;
    }

    /**
     * graycode公式法
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode1(int n) {
        List<Integer> res = new ArrayList();
        if (n == 0) {
            res.add(0);
            return res;
        }

        int b = (int)Math.pow(2, n);
        for(int i = 0; i < b; i++) {
            res.add(i ^ (i >> 1));
        }

        return res;
    }
}

