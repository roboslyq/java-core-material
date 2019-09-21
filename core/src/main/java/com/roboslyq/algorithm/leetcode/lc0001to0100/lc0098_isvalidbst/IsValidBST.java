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

/**
 *
 * 〈迭代遍列法，注意有坑，普通的遍列方法不能保证整体是一个BST〉
 * @author luo.yongqian
 * @create 2019/9/20
 * @since 1.0.0
 */
public class IsValidBST {
    public static void main(String[] args) {
        IsValidBST isValidBST = new IsValidBST();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(1);
        root.left = left;
        System.out.println(isValidBST.isValidBST(root));

    }
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        //双端队列()
        LinkedList<TreeNode> queue = new LinkedList<>();
        //当前迭代节点初始化
        queue.addLast(root);
        boolean res = true;
        while (!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            TreeNode left = currNode.left;
            TreeNode right = currNode.right;
            if(left != null){
                if(left.val >=  currNode.val){
                    res = false;
                    break;
                }
                queue.addLast(left);
            }
            if(right != null){
                if(right.val <=  currNode.val &&  right.val >= root.val ){
                    res = false;
                    break;
                }
                queue.addLast(right);
            }
        }
        return res;
    }
}