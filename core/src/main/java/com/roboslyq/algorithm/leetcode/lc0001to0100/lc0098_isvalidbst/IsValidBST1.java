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

import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * 〈中序遍列的顺序为左中右，如果左中右升序，刚好符合二叉搜索树的定义〉
 * @author luo.yongqian
 * @create 2019/9/20
 * @since 1.0.0
 */
public class IsValidBST1 {
    public static void main(String[] args) {
        IsValidBST1 isValidBST = new IsValidBST1();
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println(isValidBST.isValidBST(root));

    }
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        //前一节点值（中序遍列后一节点必须大于此值才是正确的BST）
        double inorder = - Double.MAX_VALUE;
        //双端队列()
        Stack<TreeNode> stack = new Stack<>();
        //当前迭代节点初始化
        boolean res = true;
        while (root!= null || !stack.isEmpty()) {
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.val <= inorder ) {
                res = false;
                break;
            }
            inorder = root.val;
            root = root.right;
        }
        return res;
    }

    public boolean isValidBST1(TreeNode root) {
        if(root == null) return true;
        //前一节点值（中序遍列后一节点必须大于此值才是正确的BST）
        double inorder = - Double.MAX_VALUE;
        //双端队列()
        Stack<TreeNode> stack = new Stack<>();
        //当前迭代节点初始化
        boolean res = true;
        while (root!= null || !stack.isEmpty()) {
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.val <= inorder ) {
                res = false;
                break;
            }
            inorder = root.val;
            root = root.right;
        }
        return res;
    }
}