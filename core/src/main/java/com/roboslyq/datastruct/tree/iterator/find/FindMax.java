package com.roboslyq.datastruct.tree.iterator.find;

import com.roboslyq.datastruct.tree.BinaryTreeNode;
import com.roboslyq.datastruct.tree.BinaryTreeUtil;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 对于普通无序的二叉树，找最大值或者最小值或者找某一元素是否存在，需要对树进行遍列。
 * 其中先序，中序，后序，层次遍列等都可以。只需要一个标识值来判断是否符合当前要求即可。
 */
public class FindMax {
    public static void main(String[] args) {
        BinaryTreeNode headNode = BinaryTreeUtil.generate1(4);
        FindMax findMax = new FindMax();
        System.out.println(findMax.findMax(headNode));
        System.out.println(findMax.findMax1(headNode));
        System.out.println(findMax.findMax2(headNode));
    }

    /**
     * 迭归法
     * @param treeNode
     */
    public int findMax(BinaryTreeNode<Integer> treeNode){
        //定义初始值
        int max = Integer.MIN_VALUE;
        if(treeNode != null){
            int root_val = treeNode.getT();
            int left_max = findMax(treeNode.getLeftNode());
            int right_max = findMax(treeNode.getRightNode());
            max = left_max > right_max ? left_max:right_max;
            if(root_val > max) max = root_val;
        }
        return max;
    }
    /**
     * 迭代法
     * @param treeNode
     */
    public int findMax1(BinaryTreeNode<Integer> treeNode){
        //定义初始值
        int max = Integer.MIN_VALUE;
        LinkedList<BinaryTreeNode> linkedList = new LinkedList<>();
        linkedList.push(treeNode);
        BinaryTreeNode<Integer> cur;
        while (!linkedList.isEmpty()){
            cur = linkedList.pop();
            if(cur.getT() >= max) max = cur.getT();
            if(cur.getLeftNode() != null) linkedList.addLast(cur.getLeftNode());
            if(cur.getRightNode() != null) linkedList.addLast(cur.getRightNode());
        }
        return max;
    }
    /**
     * 前序遍列
     * @param treeNode
     */
    public int findMax2(BinaryTreeNode<Integer> treeNode){
        //定义初始值
        int max = Integer.MIN_VALUE;
        Stack<BinaryTreeNode> binaryTreeNodes = new Stack<>();
        while(true){
            while(treeNode != null){//当前节点不为空，直接继续处理，添加到栈
                if(treeNode.getT() >= max) max = treeNode.getT();
                binaryTreeNodes.push(treeNode);
                treeNode = treeNode.getLeftNode();
            }
            if(binaryTreeNodes.isEmpty()) break;
            treeNode = binaryTreeNodes.pop();
            treeNode = treeNode.getRightNode();
        }
        return max;
    }

}
