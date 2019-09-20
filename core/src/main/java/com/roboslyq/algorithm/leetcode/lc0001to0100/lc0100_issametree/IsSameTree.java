/**
 * Copyright (C), 2015-2019
 * FileName: IsSameTree
 * Author:   luo.yongqian
 * Date:     2019/9/20 17:34
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/20 17:34      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0100_issametree;

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.LinkedList;

/**
 *
 * 〈采用层遍列解决此题〉
 * @author luo.yongqian
 * @create 2019/9/20
 * @since 1.0.0
 */
public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //双端队列()
        LinkedList<TreeNode> p_queue = new LinkedList<>();
        LinkedList<TreeNode> q_queue = new LinkedList<>();
        //当前迭代节点初始化
        p_queue.addLast(p);
        q_queue.addLast(q);
        boolean res = true;
        while (!p_queue.isEmpty()) {
            TreeNode p_node = p_queue.poll();
            TreeNode q_node = q_queue.poll();

            if((p_node == null && q_node != null)
                ||  (p_node != null && q_node == null)
                || (p_node != null  && p_node.val != q_node.val)
            ){//不相等时直接停止循环返回结果
                res  = false;
                break;
            }else{
                if(p_node == null){
                    continue;
                }
                p_queue.addLast(p_node.left);
                p_queue.addLast(p_node.right);
                q_queue.addLast(q_node.left);
                q_queue.addLast(q_node.right);
            }
        }
        return res;
    }
}