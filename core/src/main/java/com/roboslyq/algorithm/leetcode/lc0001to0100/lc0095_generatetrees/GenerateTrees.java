/**
 * Copyright (C), 2015-2019
 * FileName: GenerateTrees
 * Author:   luo.yongqian
 * Date:     2019/9/17 16:34
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/17 16:34      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0095_generatetrees;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/9/17
 * @since 1.0.0
 */

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class GenerateTrees {
    public static void main(String[] args) {

    }
    /**
     * @param n 整数个数
     * @return 该区间可用的BST集合
     */
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    /**
     * 该递归函数的定义是：利用[left, right]范围内的整数生成所有可能生成的二叉树集合。
     * @param left  左边界
     * @param right 右边界
     * @return 该区间可用的BST集合
     */
    private List<TreeNode> generateTrees(int left, int right) {

        List<TreeNode> result = new ArrayList<>();

        // 递归的终止条件：如果left == right，说明只有一个树，自然只有一个节点
        if (left == right) {
            TreeNode treeNode = new TreeNode(left);
            result.add(treeNode);
            return result;
        }

        // 递归的过程
        for (int i = left; i <= right; i++) {
            // 此时节点i只有右子树
            if (i == left) {
                //递归调用本函数求出所有可能的右孩子
                List<TreeNode> rightTreeNodeList = generateTrees(left + 1, right);
                for (TreeNode rightTreeNode : rightTreeNodeList) {
                    // 对每一个可能的右孩子，都可以拼装出一棵树，将其放进result里
                    TreeNode tempNode = new TreeNode(i);
                    tempNode.right = rightTreeNode;
                    result.add(tempNode);
                }
                //此时节点i只有左子树
            } else if (i == right) {
                //递归调用本函数求出所有可能的左孩子
                List<TreeNode> leftTreeNodeList = generateTrees(left, right - 1);
                for (TreeNode leftTreeNode : leftTreeNodeList) {
                    //对每一个可能的左孩子，都可以拼装出一棵树，将其放进result里
                    TreeNode tempNode = new TreeNode(i);
                    tempNode.left = leftTreeNode;
                    result.add(tempNode);
                }
            } else {
                // 递归调用本函数求出所有可能的左孩子
                List<TreeNode> leftTreeNodeList = generateTrees(left, i - 1);
                // 递归调用本函数求出所有可能的右孩子
                List<TreeNode> rightTreeNodeList = generateTrees(i + 1, right);
                for (TreeNode leftTreeNode : leftTreeNodeList) {
                    for (TreeNode rightTreeNode : rightTreeNodeList) {
                        // 拼装过程
                        TreeNode tempNode = new TreeNode(i);
                        tempNode.left = leftTreeNode;
                        tempNode.right = rightTreeNode;
                        result.add(tempNode);
                    }
                }
            }
        }
        return result;
    }
}