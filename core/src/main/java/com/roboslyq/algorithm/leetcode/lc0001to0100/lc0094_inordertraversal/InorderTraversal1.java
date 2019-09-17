/**
 * Copyright (C), 2015-2019
 * FileName: InorderTraversal
 * Author:   luo.yongqian
 * Date:     2019/9/17 12:43
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/17 12:43      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0094_inordertraversal;

import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 〈二叉树中序遍列〉
 * 递代法：
 * 二叉树遍列分前中后，均以顶点位置命名。顶点在左就是前，顶点在中间就是中，顶点在后就是后
 * @author luo.yongqian
 * @create 2019/9/17
 * @since 1.0.0
 */
public class InorderTraversal1 {
    public static void main(String[] args) {

    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //临时保存过程中的节点
        Stack<TreeNode> stack = new Stack<>();
        //当前节点
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
            //将所有的左节点入栈
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            //左节点出栈
            curr = stack.pop();
            //保存左节点结果
            res.add(curr.val);
            //替换左节点为右节点，继续迭代
            curr = curr.right;
        }
        return res;
    }
}