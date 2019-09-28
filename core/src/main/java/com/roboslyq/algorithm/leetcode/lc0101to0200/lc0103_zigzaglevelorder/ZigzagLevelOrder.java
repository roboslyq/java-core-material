package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0103_zigzaglevelorder;

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 与lc0102基本一样，只修改了两点：
 * 1、将数据结构修改为栈：不修改也可以
 * 2、加了当前层标识，用来判断方向是向左还是向右
 */
public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null ) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        //双端队列
        Stack<TreeNode> queueCurr = new Stack<>();
        Stack<TreeNode> queueNext = new Stack<>();
        //当前迭代节点初始化
        queueCurr.push(root);
        int curLevel = 1;
        while (!queueCurr.isEmpty()) {
            List<Integer> nodeX = new ArrayList<>();
            while (!queueCurr.isEmpty()) {//当前节点不为空,如果当前节点为空，处理栈中下一个节点。
                TreeNode currNode = queueCurr.pop();
                nodeX.add(currNode.val);
                if(curLevel % 2 == 0){
                    if(currNode.right != null){
                        queueNext.push(currNode.right);
                    }
                    if(currNode.left != null){
                        queueNext.push(currNode.left);
                    }
                }else{
                    if(currNode.left != null){
                        queueNext.push(currNode.left);
                    }
                    if(currNode.right != null){
                        queueNext.push(currNode.right);
                    }
                }

            }
            //处理当前节点的右节点(左节点入栈时就已经处理过了)
            Stack<TreeNode> queuetemp = queueCurr;
            queueCurr = queueNext;
            queueNext = queuetemp;
            res.add(nodeX);
            curLevel++;
        }
        return res;
    }
}
