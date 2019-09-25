/**
 * Copyright (C), 2015-2019
 * FileName: LevelOrder
 * Author:   luo.yongqian
 * Date:     2019/9/25 14:01
 * Description: 层遍列
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/25 14:01      1.0.0               创建
 */
package com.roboslyq.datastruct.tree.iterator;

import com.roboslyq.datastruct.tree.BinaryTreeNode;
import com.roboslyq.datastruct.tree.BinaryTreeUtil;

import java.util.LinkedList;

/**
 *
 * 〈层遍列 BSF breadth search firstly〉
 * @author luo.yongqian
 * @create 2019/9/25
 * @since 1.0.0
 */
public class LevelOrder {
    public static void main(String[] args) {
        BinaryTreeNode headNode = BinaryTreeUtil.generate1(4);
        LevelOrder levelOrder = new LevelOrder();
        levelOrder.levelOrder(headNode);
    }

    /**
     * 迭代完成
     * @param treeNode
     */
    public void levelOrder(BinaryTreeNode treeNode){
        LinkedList<BinaryTreeNode> linkedList = new LinkedList<>();
        linkedList.push(treeNode);
        BinaryTreeNode cur;
        while (!linkedList.isEmpty()){
            cur = linkedList.pop();
            System.out.println(cur.getT());
            if(cur.getLeftNode() != null) linkedList.addLast(cur.getLeftNode());
            if(cur.getRightNode() != null) linkedList.addLast(cur.getRightNode());
        }
    }

}