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
        reverseBetween.reverseBetween(l1,1,5);
        while (l1 != null){
            System.out.println(l1.val);
            l1 = l1.next;
        }
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n ) return head;
        ListNode before_start_reverse = null;
        ListNode start_reverse = null;
        ListNode pre = null;//反转链条的前置节点
        ListNode cur = head; //初始化cur,即相当于下面for循环的i=1
        ListNode tmp = null;
        for (int i = 0; i < Integer.MAX_VALUE ; i++) {
            if(i == m-1){//反转前处理(超始位置要取前，因为需要保存前一节点)
                if(m==1){
                    start_reverse = cur;
                }
                else{
                    start_reverse = cur.next;
                }
                pre = start_reverse;
                before_start_reverse = cur;
                continue;
            }
            if(i>= m && i < n){//反转中处理
                tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
                continue;
            }
            if(i == n) {//反转结尾处理
                tmp = cur.next;
                cur.next = pre;
                before_start_reverse.next = cur;
                start_reverse.next = tmp;

                break;
            }
            cur = cur.next;
        }
        return head;
    }
}
