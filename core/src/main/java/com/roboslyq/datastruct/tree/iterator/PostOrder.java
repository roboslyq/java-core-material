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
 *〈实现二叉树的后序遍列〉
 * @author luo.yongqian
 * @create 2019/9/25
 * @since 1.0.0
 */
public class PostOrder {
    public static void main(String[] args) {
        BinaryTreeNode headNode = BinaryTreeUtil.generate1(4);
        BinaryTreeNode headNode1 = BinaryTreeUtil.generate1(4);
        PostOrder preOrder = new PostOrder();
        preOrder.preOrder1(headNode);
        System.out.println("-------------------");
        preOrder.preOrder2(headNode1);
    }

    /**
     * 递归后序遍列
     * @param treeNode
     */
    public void preOrder1(BinaryTreeNode treeNode){
        if(treeNode == null) return;
        preOrder1(treeNode.getLeftNode());
        preOrder1(treeNode.getRightNode());
        System.out.println(treeNode.getT());
    }
    /**
     * //TODO
     * 迭代后序遍列（DSF深度优先遍列)
     * 此遍列与前中序遍列不一样。因为先左后右，然后中，在左到右时要通过中，但此时不处理中。
     * 要在右处理后返回处理中。因为要记录当前遍列是否需要处理中。
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