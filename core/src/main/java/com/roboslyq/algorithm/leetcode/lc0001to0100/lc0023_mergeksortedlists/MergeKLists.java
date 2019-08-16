/**
 * Copyright (C), 2015-2019
 * FileName: MergeKLists
 * Author:   luo.yongqian
 * Date:     2019/8/2 17:39
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/8/2 17:39      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0023_mergeksortedlists;

/**
 * 分治法，可以考虑多线程
 * @author luo.yongqian
 * @create 2019/8/2
 * @since 1.0.0
 * [[1,4,5],[1,3,4],[2,6]]
 * [1,1,2,3,4,4,5,6]
 */
public class MergeKLists {
    public static void main(String[] args) {
        MergeKLists mk = new MergeKLists();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        ListNode res = mk.mergeKLists(new ListNode[]{l1, l2, l3});
        while (res != null){
            System.out.print(res.val + "-");
            res = res.next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
       return mergeTwo(lists,0,lists.length-1);
    }

    /**
     * 参考ForkJoin框架分治思想
     * @param listNode
     * @param startIndex
     * @param endIndex
     * @return
     */
    public ListNode  mergeTwo(ListNode[] listNode,int startIndex,int endIndex){
        ListNode tmp = new ListNode(0);
        //创建结果索引，因为tmp索引会随着计算不断改变，崦result一直指向临时头节点。
        ListNode result = tmp;
        //间隔为1，合并处理
        if( endIndex - startIndex == 1){
            ListNode node1 = listNode[startIndex];
            ListNode node2 = listNode[endIndex];
            while(node1 != null && node2 != null){
                if(node1.val <= node2.val){
                    tmp.next = new ListNode(node1.val);
                    node1 = node1.next;
                }else {
                    tmp.next = new ListNode(node2.val);
                    node2 = node2.next;
                }
                tmp = tmp.next;
            }
            //处理ListNode节点长度不一致情况(即另一个ListNode的剩余节点)
            tmp.next = node1 == null ? node2 : node1;
            return result.next;
        }else if(endIndex - startIndex == 0) {//基数，只有一个ListNode的情况，直接返回
            return listNode[startIndex];
        }else{//间隔大于2，继承拆份任务
            int middleIndex = (endIndex + startIndex)/2;
            //如果不是两个数，则继续分解
            ListNode tmp1 =  mergeTwo(listNode,startIndex,middleIndex);
            ListNode tmp2 = mergeTwo(listNode,middleIndex+1,endIndex);
            ListNode[] listTmp = {tmp1,tmp2};
            return mergeTwo(listTmp,0,1);
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}