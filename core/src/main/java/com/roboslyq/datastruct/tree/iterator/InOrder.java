/**
 * Copyright (C), 2015-2019
 * FileName: PreOrder
 * Author:   luo.yongqian
 * Date:     2019/9/25 13:28
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/25 13:28      1.0.0               创建
 */
package com.roboslyq.datastruct.tree.iterator;

import com.roboslyq.datastruct.tree.BinaryTreeNode;
import com.roboslyq.datastruct.tree.BinaryTreeUtil;

import java.util.Stack;

/**
 *
 *〈实现二叉树的中序遍列〉
 * 中序遍列是有序的，可以通过此遍列判断是不是BST树
 * @author luo.yongqian
 * @create 2019/9/25
 * @since 1.0.0
 */
public class InOrder {
    public static void main(String[] args) {
        BinaryTreeNode headNode = BinaryTreeUtil.generate1(4);
        BinaryTreeNode headNode1 = BinaryTreeUtil.generate1(4);
        InOrder preOrder = new InOrder();
        preOrder.preOrder1(headNode);
        System.out.println("-------------------");
        preOrder.preOrder2(headNode1);
    }

    /**
     * 递归中序遍列
     * @param treeNode
     */
    public void preOrder1(BinaryTreeNode treeNode){
        if(treeNode == null) return;
        preOrder1(treeNode.getLeftNode());
        System.out.println(treeNode.getT());
        preOrder1(treeNode.getRightNode());
    }
    /**
     * 迭代中序遍列（DSF深度优先遍列)
     * @param treeNode
     */
    public void preOrder2(BinaryTreeNode treeNode){
        Stack<BinaryTreeNode> binaryTreeNodes = new Stack<>();
        while(true){
            while(treeNode != null){//当前节点不为空，直接继续处理，添加到栈
                binaryTreeNodes.push(treeNode);
                treeNode = treeNode.getLeftNode();
            }
            if(binaryTreeNodes.isEmpty()) return;
            treeNode = binaryTreeNodes.pop();
            //与前序的差别
            System.out.println(treeNode.getT());
            treeNode = treeNode.getRightNode();
        }
    }

}