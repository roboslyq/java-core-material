package com.roboslyq.datastruct.tree.find;

import com.roboslyq.datastruct.tree.BinaryTreeNode;
import com.roboslyq.datastruct.tree.BinaryTreeUtil;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 在树中找某个元素是否存在
 */
public class FindInTree {
    public static void main(String[] args) {
        BinaryTreeNode headNode = BinaryTreeUtil.generate1(4);
        FindInTree findMax = new FindInTree();
        System.out.println(findMax.findInTree(headNode,16));
        System.out.println(findMax.findInTree1(headNode,16));
        System.out.println(findMax.findInTree1(headNode,15));

    }

    /**
     * 迭归法
     * @param treeNode
     */
    public boolean findInTree(BinaryTreeNode<Integer> treeNode, int val){
        //定义初始值
        boolean exist_flag = false;
        if(treeNode != null){
            int root_val = treeNode.getT();
            if(root_val == val){
                exist_flag = true;
            }else{
                boolean exist_left = findInTree(treeNode.getLeftNode(),val);
                if(exist_left) {
                    exist_flag = true;
                }else{
                    boolean exist_right = findInTree(treeNode.getRightNode(),val);
                    if(exist_right) {
                        exist_flag = true;
                    }
                }
            }
        }
        return exist_flag;
    }
    /**
     * 迭代法
     * @param treeNode
     */
    public boolean findInTree1(BinaryTreeNode<Integer> treeNode,int val){
        //定义初始值
        boolean exist_flag = false;
        LinkedList<BinaryTreeNode> linkedList = new LinkedList<>();
        linkedList.push(treeNode);
        BinaryTreeNode<Integer> cur;
        while (!linkedList.isEmpty()){
            cur = linkedList.pop();
            if(cur.getT() == val){
                exist_flag =  true;
                break;
            }else{
                if(cur.getLeftNode() != null) linkedList.addLast(cur.getLeftNode());
                if(cur.getRightNode() != null) linkedList.addLast(cur.getRightNode());
            }
        }
        return exist_flag;
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
