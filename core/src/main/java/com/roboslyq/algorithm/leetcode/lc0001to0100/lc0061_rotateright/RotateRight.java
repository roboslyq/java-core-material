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
        ListNode countHead = head;
        List<ListNode> arrayList = new ArrayList<>();
        arrayList.add(head);
        int nodeLen = 1;
        while (countHead.next != null) {
            arrayList.add(countHead.next);
            nodeLen++;
            countHead = countHead.next;
        }
        if(nodeLen == 1) return head;
        //循环后，countHead即为tail
        int recCount = k%nodeLen;
        //刚好整除，表示不需要移动，直接返回原ListNode
        if(recCount == 0) return head;
        //结果head所在索引
        int resHeadIndex = nodeLen - recCount;
        //设置结果head
        ListNode resHead  = arrayList.get(resHeadIndex);
        //设置新的tail的next为null
        arrayList.get(resHeadIndex - 1 ).next = null ;
        //将当前tail指向当前head
        arrayList.get(nodeLen-1).next = head;

        return resHead;
    }
}
