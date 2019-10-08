package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0110_isbalanced;

import com.roboslyq.algorithm.leetcode.TreeNode;

/**
 * 注意，仅判断是否高度平衡
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsBalanced {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode rootLeft = new TreeNode(9);
        TreeNode rootRight = new TreeNode(20);
        TreeNode rightLeft = new TreeNode(15);
        TreeNode rightRight = new TreeNode(7);
        root.left = rootLeft;
        root.right = rootRight;
        rootRight.left = rightLeft;
        rootRight.right = rightRight;
        IsBalanced isBalanced = new IsBalanced();
        System.out.println(isBalanced.isBalanced(root));
    }
    public boolean isBalanced(TreeNode root) {
        return helper(root) != -1;
    }

    /**
     * 方法一：递归
     * @param treeNode
     * @return 平衡时返回树高度，非平衡时返回-1
     */
    public int helper(TreeNode treeNode){
        //节点为空，表示在最底层，此时节点高度为0
        if(treeNode == null) return 0;
        //递归获取左节点高度，如果左节点高度为-1，表示不是合法的平衡树，直接返回
        int left = helper(treeNode.left);
        if(left == -1){
            return -1;
        }
        //递归获取右节点高度，如果左节点高度为-1，表示不是合法的平衡树，直接返回
        int right = helper(treeNode.right);
        if(right == -1){
            return -1;
        }
        //左右节点均是合法的平衡树，判断当前树(包含当前节点，需要比较左树两树的高度差是否超过1)
        int diff = left - right;
        if(Math.abs(diff) >1 ){//如果超过1，则返回
            return -1;
        }else {
            //没有超过，当前节点高度+1。通过此思想，可以返回当前节点所在的树的高度
            return Math.max(left,right) + 1;
        }
    }
}
