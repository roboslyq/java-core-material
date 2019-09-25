package com.roboslyq.algorithm.leetcode.lc0101to200.lc0101_issymmetric;

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsSymmetric {
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
        IsSymmetric isSymmetric = new IsSymmetric();
        System.out.println(isSymmetric.isSymmetric(root));
    }


    /**
     * 递归法：
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root,root);
    }

    public boolean isSymmetric(TreeNode left ,TreeNode right){
        /**
         * 处理终止条件：
         */
        //排除两个为空的情况
        if(left == null && right == null) return true;
        //肯定只有一个为空另一个不为空，因为两个为空已经排除。此条件返回false
        if(left == null || right == null) return false;
        //两个不为空，继续判断
        return (left.val == right.val)
               && isSymmetric(left.left,right.right)
               && isSymmetric(left.right,right.left);

    }
}
