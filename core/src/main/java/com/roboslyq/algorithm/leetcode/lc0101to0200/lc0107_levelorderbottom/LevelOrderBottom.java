package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0107_levelorderbottom;

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 与lc0102一样，只是结果生成始终添加在head
 */
public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null ) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        //双端队列
        LinkedList<TreeNode> queueCurr = new LinkedList<>();
        LinkedList<TreeNode> queueNext = new LinkedList<>();
        //当前迭代节点初始化
        queueCurr.addLast(root);
        while (!queueCurr.isEmpty()) {
            List<Integer> nodeX = new ArrayList<>();
            while (!queueCurr.isEmpty()) {//当前节点不为空,如果当前节点为空，处理栈中下一个节点。
                TreeNode currNode = queueCurr.poll();
                nodeX.add(currNode.val);
                if(currNode.left != null){
                    queueNext.addLast(currNode.left);
                }
                if(currNode.right != null){
                    queueNext.addLast(currNode.right);
                }
            }
            //处理当前节点的右节点(左节点入栈时就已经处理过了)
            LinkedList<TreeNode> queuetemp = queueCurr;
            queueCurr = queueNext;
            queueNext = queuetemp;
            //此处不一样
            res.add(0,nodeX);
        }
        return res;
    }
}
