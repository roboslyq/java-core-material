/**
 * Copyright (C), 2015-2019
 * FileName: InorderTraversal
 * Author:   luo.yongqian
 * Date:     2019/9/17 12:43
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/17 12:43      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0094_inordertraversal;

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 〈二叉树中序遍列〉
 * 递归法：
 * 二叉树遍列分前中后，均以顶点位置命名。顶点在左就是前，顶点在中间就是中，顶点在后就是后
 * @author luo.yongqian
 * @create 2019/9/17
 * @since 1.0.0
 */
public class InorderTraversal {
    public static void main(String[] args) {

    }
    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorderTraversal_recursion(root);
        return res;
    }
    public void inorderTraversal_recursion(TreeNode root){
        if(root == null){
            return ;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        inorderTraversal(left);
        res.add(root.val);
        inorderTraversal(right);
    }
}