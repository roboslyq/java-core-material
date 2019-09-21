/**
 * Copyright (C), 2015-2019
 * FileName: IsValidBST
 * Author:   luo.yongqian
 * Date:     2019/9/20 17:50
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/20 17:50      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0098_isvalidbst;

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.Stack;

/**
 *
 * 〈递归法〉
 * @author luo.yongqian
 * @create 2019/9/20
 * @since 1.0.0
 */
public class IsValidBST2 {
    public static void main(String[] args) {
        IsValidBST2 isValidBST = new IsValidBST2();
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(5);
        TreeNode r = new TreeNode(2);
        root.left = left;
        root.right = right;
        right.left = r;
        System.out.println(isValidBST.isValidBST(root));


    }
    public boolean isValidBST(TreeNode root) {
        //根节点的左右边界为null
        return helper(root, null, null);
    }

    /**
     * 节点值：右边遍列，lower保存爷节点值为最低，更新upper为父节点值
     *         左遍列，upper为爷节点值，更新lower为父节点值
     * @param node
     * @param lower
     * @param upper
     * @return
     */
    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;
        int val = node.val;
        //lower不为空，表示非根节点，判断当前节点val与下边界lower关系
        // val应该大于下边界，如果小于等于下边界返回false
        if (lower != null && val <= lower) return false;
        //upper不为空，表示非根节点，判断当前节点val与上边界upper关系
        //val应该小于上边界，如果大于上边界则返回false
        if (upper != null && val >= upper) return false;
        //右节点遍列，当前val为lower边界，因为右边的子节点大于当前节点，所以不用考虑上限
        if (!helper(node.right, val, upper)) return false;
        //左节点遍列，当前val为upper边界，lower边界不需要(为爷节点的值)更新
        if (!helper(node.left, lower, val)) return false;
        return true;
    }
}