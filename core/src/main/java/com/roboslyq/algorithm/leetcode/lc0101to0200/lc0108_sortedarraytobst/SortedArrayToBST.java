/**
 * Copyright (C), 2015-2019
 * FileName: SortedArrayToBST
 * Author:   luo.yongqian
 * Date:     2019/10/7 16:42
 * Description: 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。  本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。  示例:  给定有序数组: [-10,-3,0,5,9],  一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：        0      / \    -3   9    /   /  -10  5  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/10/7 16:42      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0108_sortedarraytobst;

import com.roboslyq.algorithm.leetcode.TreeNode;
import com.roboslyq.algorithm.leetcode.TreeNodeUtil;

/**
 *
 * 〈将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * >
 * @author luo.yongqian
 * @create 2019/10/7
 * @since 1.0.0
 */
public class SortedArrayToBST {
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{0, -3, 9, -10, null, 5};
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        TreeNode res = sortedArrayToBST.sortedArrayToBST(nums);
        TreeNodeUtil.inOrder(res);

    }
    public TreeNode sortedArrayToBST(Integer[] nums) {
        return generateBST(nums,0,nums.length-1);
    }

    public TreeNode generateBST(Integer[] nums,int leftNodeIndex,int rightNodeIndex){
        int middleIndex = (rightNodeIndex + leftNodeIndex)/2;
        if(leftNodeIndex >= rightNodeIndex) return null;
        TreeNode curRoot = new TreeNode(nums[middleIndex]);
        curRoot.left = generateBST(nums,leftNodeIndex,middleIndex);
        curRoot.right = generateBST(nums,middleIndex+1,rightNodeIndex);
        return curRoot;
    }
}