package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0061_rotateright;


import com.roboslyq.algorithm.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、循环listnode.length次时 ，链表不变。所以循环次数可以为  k % istnode.length 。即k与ListNode长度取余
 * 2、旋转方法即将tail指向head,然后倒数第二个next指向null即可。
 */
public class RotateRight {
    public static void main(String[] args) {

    }
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k ==0) return head;
        int nodeLen = 1;
        ListNode countHead = head;
        List<ListNode> arrayList = new ArrayList<>();
        arrayList.add(head);
        while (countHead.next != null) {
            arrayList.add(countHead.next);
           nodeLen++;
           countHead = countHead.next;
        }
        if(nodeLen == 1) return head;
        //循环后，countHead即为tail
        int recCount = k%nodeLen;
        if(recCount == 0) return head;
         int resHeadIndex = nodeLen - recCount;

         ListNode resHead  = arrayList.get(resHeadIndex);
         //设置新的tail的next为null
         arrayList.get(resHeadIndex - 1 ).next = null ;
         arrayList.get(nodeLen-1).next = head;
         return resHead;
    }
}
