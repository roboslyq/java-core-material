package com.roboslyq.algorithm.leetcode.lc0025_reversekgroup;

import com.roboslyq.algorithm.leetcode.ListNode;

import java.util.Stack;

/**
 * 使用栈操作
 */
public class ReverseKGroup {
    public static void main(String[] args) {
        ReverseKGroup reverseKGroup = new ReverseKGroup();
        ListNode listNode1 = new ListNode(1);

        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode res = reverseKGroup.reverse(listNode1);
        while (res != null)
        {
            System.out.println(res.val);
            res = res.next;
        }
    }
    private ListNode reverse(ListNode head) {
        ListNode curResult = null;//保存翻转后结果ListNode的head元素
        ListNode curr = head;//当前待翻转的元素，即翻转前的head元素
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = curResult;
            curResult = curr;
            curr = next;
        }
        return curResult;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode resHead = null;
        ListNode curReverseHead = head;
        int i = 0;
        while(true){
            if(i < k - 1){//入栈，
                stack.push(head);
                head = head.next;
                i++;
            }else{
                head = head.next;
                ListNode headNew = stack.pop();

            }
        }

    }
}
