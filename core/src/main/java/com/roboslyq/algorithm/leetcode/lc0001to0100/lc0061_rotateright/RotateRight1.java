package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0061_rotateright;


import com.roboslyq.algorithm.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、循环listnode.length次时 ，链表不变。所以循环次数可以为  k % istnode.length 。即k与ListNode长度取余
 * 2、旋转方法即将tail指向head,然后倒数第二个next指向null即可。
 */
public class RotateRight1 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(3);
//        ListNode l4 = new ListNode(4);
//        ListNode l5 = new ListNode(5);
        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = l5;
        RotateRight1 rotateRight1 = new RotateRight1();
        ListNode res = rotateRight1.rotateRight(l1,1);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k ==0 || head.next == null) return head;
        ListNode countHead = head;
        int nodeLen = 1;
        while (countHead.next != null) {
            nodeLen++;
            countHead = countHead.next;
        }
        //循环后，countHead即为tail
        ListNode tail = countHead;
        int recCount = k%nodeLen;
        //刚好整除，表示不需要移动，直接返回原ListNode
        if(recCount == 0) return head;
        //结果head所在索引
        int resHeadIndex = nodeLen - recCount;
        //设置结果head
        ListNode resHead = head;
        ListNode resTail = null;
        for(int i=0;i<= resHeadIndex;i++){
            if(i > 0 ){//因为resHead已经赋值head，所以0不处理
                resHead = resHead.next;
            }
            //处理resHead
            if(i == resHeadIndex - 1 ){
                resTail = resHead;
            }
        }
        //设置新的tail的next为null
        resTail.next = null ;
        //将当前tail指向当前head
        tail.next = head;

        return resHead;
    }
}
