package com.roboslyq.datastruct.tree;

import java.util.LinkedList;
import java.util.Stack;

public class BinaryTreeUtil {
    /**
     * 生成level层的满二叉树
     * @param level 二叉树的层数
     * @return
     */
    public static BinaryTreeNode generate1(int level){
        //使用又端队列(Queue来操作，从左到右按顺序保存下一节点。所以入队是队尾，出队是队首)
        LinkedList<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        //，初始化递归参数，构造首节点
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1);
        queue.addLast(root);
        if(level < 0 ) return null;
        if(level == 1) return root;
        int startVal = 2;
        int totalLevel = level;
        int curLevel = 2;
        //递归使用
        generate(queue,startVal,totalLevel,curLevel);
        return root;
    }

    /**
     *
     * @param root  当前层的节点栈，栈顶是最右节点，从右向左
     * @param startVal
     * @param totalLevel 从1开始计算（1只有一个根节点)
     * @param curLevel 当前层
     */
    public static void generate(LinkedList<BinaryTreeNode<Integer>> root, int startVal, int totalLevel, int curLevel ){
        LinkedList<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        int nums = (int)Math.pow(2,curLevel-1-1); //上一层节点个数
        int nextNodeMaxRightIndex =  0;
        for(int i = 1;i<= nums ;i++){
            BinaryTreeNode<Integer> curNode = root.poll();
            BinaryTreeNode curLeft = new BinaryTreeNode(startVal + nextNodeMaxRightIndex++);
            BinaryTreeNode curRight =  new BinaryTreeNode(startVal + nextNodeMaxRightIndex++);
            curNode.setRightNode(curRight);
            curNode.setLeftNode(curLeft);
            if(curLevel < totalLevel){
                queue.addLast(curLeft);
                queue.addLast(curRight);
            }
        }
        if(curLevel < totalLevel){
            generate(queue,startVal + nextNodeMaxRightIndex,totalLevel,curLevel+1);
        }
    }
    /**
     * 递归法，前序遍列
     * @param binaryTreeNode
     */
    public static void preOrder(BinaryTreeNode<Object> binaryTreeNode){
        if(binaryTreeNode != null) {
            System.out.println(binaryTreeNode.getT());
            preOrder(binaryTreeNode.getLeftNode());
            preOrder(binaryTreeNode.getRightNode());
        }
    }

    /**
     * 迭代法1，前序遍列
     * @param binaryTreeNode
     */
    public static void preOrder1(BinaryTreeNode<Object> binaryTreeNode){
            Stack<BinaryTreeNode> stack = new Stack<>();
            while (true) {
                while (binaryTreeNode != null) {
                    System.out.println(binaryTreeNode.getT());
                    stack.push(binaryTreeNode);
                    binaryTreeNode = binaryTreeNode.getLeftNode();
                }
                if(stack.isEmpty()) break;
                binaryTreeNode = stack.pop();
                binaryTreeNode = binaryTreeNode.getRightNode();
            }
    }
    /**
     * 迭代法2，前序遍列
     * @param binaryTreeNode
     */
    public static void preOrder2(BinaryTreeNode<Object> binaryTreeNode){
            Stack<BinaryTreeNode> stack = new Stack<>();
            //当前迭代节点初始化
            BinaryTreeNode curr = binaryTreeNode;
            while (curr != null || !stack.isEmpty()) {
                while (curr != null) {//当前节点不为空,如果当前节点为空，处理栈中下一个节点。
                    System.out.println(curr.getT());
                    stack.push(curr);//栈保存当前节点，并且遍列左节点入栈
                    curr = curr.getLeftNode();
                }
                //处理当前节点的右节点(左节点入栈时就已经处理过了)
                curr = stack.pop();
                curr = curr.getRightNode();
            }
    }
}
