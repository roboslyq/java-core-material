package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0102_maxdepth;

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.LinkedList;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxDepth {
    /**
     * 采用层遍列，性能不是很好！！
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        LinkedList<TreeNode> linkedListCurr = new LinkedList<>();
        LinkedList<TreeNode> linkedListNext = new LinkedList<>();
        linkedListCurr.push(root);
        int depth = 1;
        while(!linkedListCurr.isEmpty()) {
            while (!linkedListCurr.isEmpty()) {
                TreeNode cur = linkedListCurr.pop();
                TreeNode nextLeft = cur.left;
                TreeNode nextRight = cur.right;
                if(nextLeft != null) linkedListNext.addLast(nextLeft);
                if(nextRight != null)  linkedListNext.addLast(nextRight);
            }
            LinkedList tmp = linkedListCurr;
            linkedListCurr = linkedListNext;
            linkedListNext = tmp;
            if(!linkedListCurr.isEmpty())
            depth++;
        }
        return depth;
    }
}
