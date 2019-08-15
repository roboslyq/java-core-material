package com.roboslyq.algorithm.leetcode.lc0025_reversekgroup;

import com.roboslyq.algorithm.leetcode.ListNode;

import java.util.Stack;

/**
 * 使用栈操作
 */
public class ReverseKGroup {
    public static void main(String[] args) {
        ReverseKGroup rk = new ReverseKGroup();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        ListNode reverse = rk.reverse(l1);
        while (reverse != null){
            System.out.println(reverse.val);
            reverse = reverse.next;
        }

    }
    public ListNode reverse(ListNode listNode,int k){
        ListNode resHead = null;
        ListNode curr = listNode;
        int j = 0;
        while (curr != null){
            //临时保存下一个将要处理的元素
            ListNode currNext = curr.next;
            //将之前结果作为当前元素的next元素
            curr.next = resHead;
            //将处理后的节点保存到结果中
            resHead = curr;
            //切换下一个要处理的元素为当前元素
            curr = currNext;
            j++;
            if(j == k ){
                break;
            }
        }
        return resHead;
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
