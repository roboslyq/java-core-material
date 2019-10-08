package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0199_rightsideview;

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RightSideView {
    public static void main(String[] args) {

    }

    /**
     * 使用层遍列即可。每一层最右边一个元素即为目标值
     * 1、如果判断最右，通过双向队列，在每一层使用先左后右入栈。出栈最通过removeFirst,即从左出栈。
     * 当栈为空最，即为最后一个元素
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //为空输入特殊处理
        if(root == null){
            return res;
        }
        //当前层节点栈
        LinkedList<TreeNode> curLevelList = new LinkedList<>();
        //下一层节点栈
        LinkedList<TreeNode> nextLevelList = new LinkedList<>();
        //初始化当前层节点栈为根节点
        curLevelList.push(root);
        //当前正在处理的节点
        TreeNode cur;
        while (!curLevelList.isEmpty()){
            cur = curLevelList.removeFirst();
            //保存下一层的元素，从左向右保存
            if(cur.left != null){
                nextLevelList.addLast(cur.left);
            }
            if(cur.right != null){
                nextLevelList.addLast(cur.right);
            }
            //当前栈为空，即为最后一个元素。设置下一层为当前层，继续遍列
           if(curLevelList.isEmpty()){
               res.add(cur.val);//最后一个元素即为目标值，保存到结果集合中
               LinkedList<TreeNode> tmp = curLevelList;
               curLevelList = nextLevelList;
               nextLevelList = tmp;
           }
        }
        return res;
    }
}
