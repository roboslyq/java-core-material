package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0102_levelorder;

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
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
            res.add(nodeX);
        }
        return res;
    }
}
