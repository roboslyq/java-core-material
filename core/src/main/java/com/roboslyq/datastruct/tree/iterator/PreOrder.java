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
 * 〈实现二叉树的前/先序遍列〉
 * @author luo.yongqian
 * @create 2019/9/25
 * @since 1.0.0
 */
public class PreOrder {
    public static void main(String[] args) {
        BinaryTreeNode headNode = BinaryTreeUtil.generate1(4);
        BinaryTreeNode headNode1 = BinaryTreeUtil.generate1(4);
        PreOrder preOrder = new PreOrder();
        preOrder.preOrder1(headNode);
        preOrder.preOrder2(headNode1);
    }

    /**
     * 递归前序遍列
     * @param treeNode
     */
    public void preOrder1(BinaryTreeNode treeNode){
        if(treeNode == null) return;
        System.out.println(treeNode.getT());
        preOrder1(treeNode.getLeftNode());
        preOrder1(treeNode.getRightNode());
    }
    /**
     * 迭代前序遍列（DSF深度优先遍列)
     * @param treeNode
     */
    public void preOrder2(BinaryTreeNode treeNode){
        Stack<BinaryTreeNode> binaryTreeNodes = new Stack<>();
        while(true){
            while(treeNode != null){//当前节点不为空，直接继续处理，添加到栈
                System.out.println(treeNode.getT());
                binaryTreeNodes.push(treeNode);
                treeNode = treeNode.getLeftNode();
            }
            if(binaryTreeNodes.isEmpty()) return;
            treeNode = binaryTreeNodes.pop();
            treeNode = treeNode.getRightNode();
        }
    }

}