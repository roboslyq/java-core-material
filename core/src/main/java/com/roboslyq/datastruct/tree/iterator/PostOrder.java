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
 * 后序遍历递归定义：
 *      先左子树，后右子树，再根节点。
 * 后序遍历的难点：
 *      需要判断上次访问的节点是位于左子树，还是右子树。若是位于左子树，则需跳过根节点，
 *      先进入右子树，再回头访问根节点；若是位于右子树，则直接访问根节点。
 * @author luo.yongqian
 * @create 2019/9/25
 * @since 1.0.0
 */
public class PostOrder {
    public static void main(String[] args) {
//        BinaryTreeNode headNode = BinaryTreeUtil.generate1(4);
//        BinaryTreeNode headNode1 = BinaryTreeUtil.generate1(4);
        BinaryTreeNode headNode1 = BinaryTreeUtil.generate1(3);
        PostOrder postOrder = new PostOrder();
        postOrder.postOrder1(headNode1);
//        4
//        5
//        2
//        6
//        7
//        3
//        1

//        System.out.println("-------------------");
//        preOrder.postOrder1(headNode1);

    }

    /**
     * 递归后序遍列
     * @param treeNode
     */
    public void postOrder(BinaryTreeNode treeNode){
        if(treeNode == null) return;
        postOrder(treeNode.getLeftNode());
        postOrder(treeNode.getRightNode());
        System.out.println(treeNode.getT());
    }
    /**
     * 迭代后序遍列（DSF深度优先遍列)
     * 此遍列与前中序遍列不一样。因为先左后右，然后中，在左到右时要通过中，但此时不处理中。
     * 要在右处理后返回处理中。因为要记录当前遍列是否需要处理中。
     * @param treeNode
     */
    public void postOrder1(BinaryTreeNode treeNode){
        Stack<BinaryTreeNode> stack = new Stack<>();
        //记录上一次访问的节点。如果当前节点的right节点与preNode相等，则表示当前节点的右节点已经处理过了。可以处理当前节点
        BinaryTreeNode preNode = null;
        //当前节点不为空，直接继续处理，添加到栈。左节点全部入栈
        while(treeNode != null){
            stack.push(treeNode);
            treeNode = treeNode.getLeftNode();
        }
        //迭代处理
        while(!stack.isEmpty()){
            BinaryTreeNode curNode = stack.pop();
            if(curNode.getRightNode() ==  null //右节点为空，表示当前节点可以处理
                    || curNode.getRightNode() == preNode//访问的前一节点为右节点，所以需要处理
            ){
                /**
                 * 第一次进入此循环:表示处理左节点
                 */
                System.out.println(curNode.getT());
                preNode = curNode;
            }else{
                /**
                 * 表示当前节点有右节点需要处理，将当前节点重新入栈
                 */
                stack.push(curNode);//有右节点需要处理，先将当前节点入栈
                /**
                 * 获取右节点，以右节点为根节点，遍列所有的左节点
                 */
                curNode = curNode.getRightNode();
                while (curNode != null)
                {
                    stack.push(curNode);
                    curNode = curNode.getLeftNode();
                }
            }
        }
    }

}