package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0092_reversebetween;

import com.roboslyq.algorithm.leetcode.ListNode;

public class ReverseBetween {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ReverseBetween reverseBetween = new ReverseBetween();
        ListNode res = reverseBetween.reverseBetween(l1,2,4);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }

    /**
     * 分三段逻辑处理
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode before_reverse = null;//反转链条的前置节点
        ListNode start_reverse = null;//反转的起始节点
        ListNode cur = head;         //当前节点
        ListNode pre_tmp = null;     //临时节点
        //第一段逻辑，交易前的节点
        while(m > 1){
            before_reverse = cur;
            cur = cur.next;
            m--;
            n--;//n计数器
        }
        //设置开始转换节点
        start_reverse = cur;
        ListNode tmp = null;
        //交易中的节点处理（节点置换）
        while(n > 0){//此时n的值为原始n与m的差值
            tmp = cur.next;
            cur.next = pre_tmp;
            pre_tmp = cur;
            cur = tmp;
            n--;
        }
        //最后串联节点
        if(before_reverse != null){
            before_reverse.next = pre_tmp;
        }else{
            head = pre_tmp;
        }
        start_reverse.next = cur;
        return head;
    }
}
