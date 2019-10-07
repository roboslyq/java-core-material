package com.roboslyq.algorithm.leetcode;

/**
 * 一曰迭代（iterate）；二曰递归（recursion）。
 * 从“编程之美”的角度看，可以借用一句非常经典的话：“迭代是人，递归是神！”来从宏观上对二者进行把握。
 */
public class TreeNodeUtil {

    /**
     * 递归中序遍列
     * @param treeNode
     */
    public static void inOrder(TreeNode treeNode){
        if(treeNode == null) return;
        inOrder(treeNode.left);
        System.out.println(treeNode.val);
        inOrder(treeNode.right);
    }

}
