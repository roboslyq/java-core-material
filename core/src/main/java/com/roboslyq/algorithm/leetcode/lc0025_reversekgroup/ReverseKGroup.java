package com.roboslyq.algorithm.leetcode.lc0025_reversekgroup;

import com.roboslyq.algorithm.leetcode.ListNode;

import java.util.Stack;

/**
 * 使用栈操作
 */
public class ReverseKGroup {
    public static void main(String[] args) {

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
