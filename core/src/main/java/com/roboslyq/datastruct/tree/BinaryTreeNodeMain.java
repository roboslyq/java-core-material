package com.roboslyq.datastruct.tree;

import com.roboslyq.datastruct.Source;
import com.roboslyq.datastruct.SourceImpl1;

/**
 * 一曰迭代（iterate）；二曰递归（recursion）。
 * 从“编程之美”的角度看，可以借用一句非常经典的话：“迭代是人，递归是神！”来从宏观上对二者进行把握。
 */
public class BinaryTreeNodeMain {

    public static void main(String[] args) {

        BinaryTreeUtil binaryTreeUtil = new BinaryTreeUtil();
        BinaryTreeNode headNode =BinaryTreeUtil.generate1(4);
        System.out.println("start--递归法--前序遍列 : ");
        binaryTreeUtil.preOrder(headNode);
        System.out.println("end--递归法--前序遍列 ");
        System.out.println("start--迭代法1--前序遍列 : ");
        binaryTreeUtil.preOrder1(headNode);
        System.out.println("end--迭代法1--前序遍列 ");
        System.out.println("start--迭代法2--前序遍列 : ");
        binaryTreeUtil.preOrder2(headNode);
        System.out.println("end--迭代法2--前序遍列 ");
    }

}
