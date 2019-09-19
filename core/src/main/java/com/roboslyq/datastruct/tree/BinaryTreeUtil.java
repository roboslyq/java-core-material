package com.roboslyq.datastruct.tree;

import com.roboslyq.datastruct.Source;

public class BinaryTreeUtil {
    /**
     * 递归法，前序遍列
     * @param binaryTreeNode
     */
    public void preOrder(BinaryTreeNode<Source> binaryTreeNode){
        if(binaryTreeNode != null) {
            System.out.println(binaryTreeNode.getT().getId());
            preOrder(binaryTreeNode.getLeftNode());
            preOrder(binaryTreeNode.getRightNode());
        }
    }
}
