/**
 * Copyright (C), 2015-2019
 * FileName: HasPathSum
 * Author:   luo.yongqian
 * Date:     2019/9/26 17:23
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/26 17:23      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0112_haspathsum;

import com.roboslyq.algorithm.leetcode.TreeNode;
import com.roboslyq.algorithm.leetcode.lc0101to0200.lc0101_issymmetric.IsSymmetric;

import java.util.Stack;

/**
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author luo.yongqian
 * @create 2019/9/26
 * @since 1.0.0
 */
public class HasPathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        TreeNode tmp1 = new TreeNode(3);
        TreeNode tmp2 = new TreeNode(3);
        root.left = left;
        root.right = right;
        left.right = tmp1;
        right.right = tmp2;
        HasPathSum hasPathSum = new HasPathSum();
        System.out.println(hasPathSum.hasPathSum(root,4));
    }

    /**
     * 递归法
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        sum -= root.val;
        if(root.left == null && root.right == null){
            if(sum == 0) return true;
        }
        return hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
    }
}