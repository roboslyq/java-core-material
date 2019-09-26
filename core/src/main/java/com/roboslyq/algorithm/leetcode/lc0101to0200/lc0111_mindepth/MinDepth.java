package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0111_mindepth;

import com.roboslyq.algorithm.leetcode.TreeNode;

public class MinDepth {
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
        MinDepth minDepth = new MinDepth();
        System.out.println(minDepth.minDepth(root));
    }

    /**
     * 递归法
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return minDepth(root,1);
    }

    /**
     *
     * @param root  当前节点
     * @param curDepth 当前节点深度
     * @return
     */
    public int minDepth(TreeNode root, int curDepth) {
        //因为下方有条件判断，root不为空才递归，所以此处不需要判断root是否为空
        //节点的左右节点为空，表示叶子节点，返回结果
        if (root.left == null && root.right == null) {
            return curDepth;
        }
        //继续递归遍列
        int leftDepth = Integer.MAX_VALUE;
        int rightDepth = Integer.MAX_VALUE;
        if(root.left != null){//左节点不为空，遍列左节点，并且深度+1
            leftDepth =  minDepth(root.left, curDepth+1);
        }
        if (root.right != null) {//右节点不为空，遍列右节点，并且深度+1
            rightDepth =  minDepth(root.right, curDepth+1);
        }
        //返回左右两节点较小值
        return Math.min(leftDepth, rightDepth);
    }
}
