package com.roboslyq.algorithm.leetcode.lc0023_swappairs;

import com.roboslyq.algorithm.leetcode.ListNode;

/**
 * 使用交换K的通用方法
 */
public class SwapPairs {
    public static void main(String[] args) {
        SwapPairs rk = new SwapPairs();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        ListNode l8 = new ListNode(8);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        ListNode reverse = rk.swapPairs(l1);
        while (reverse != null){
            System.out.println(reverse.val);
            reverse = reverse.next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        int k = 2;
        ListNode resHead = null;
        ListNode resTail = null;
        ListNode curr = head;
        int j = 0;//已翻转元素的个数处理
        if(curr != null){
            while (j != k && curr != null ) {
                //j=0时，表示当前node为head,即翻转后的tail元素
                if(j==0){
                    resTail = curr;
                }
                //临时保存下一个将要处理的元素
                ListNode currNext = curr.next;
                //将之前结果作为当前元素的next元素
                curr.next = resHead;
                //将处理后的节点保存到结果中
                resHead = curr;
                //切换下一个要处理的元素为当前元素
                curr = currNext;
                j++;
            }
            if(j == k ){//表示可能还有next元素,递归调用翻转
                resTail.next = swapPairs(curr);
            }else{
                //元素不够，重新反转回来，结果为resHead,即最终结果
                resHead = reverse(resHead);
            }
        }
        return resHead;
    }

    /**
     * 翻转所有元素
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head){
        ListNode resHead = null;
        ListNode curr = head;
        while ( curr != null ) {
            //临时保存下一个将要处理的元素
            ListNode currNext = curr.next;
            //将之前结果作为当前元素的next元素
            curr.next = resHead;
            //将处理后的节点保存到结果中
            resHead = curr;
            //切换下一个要处理的元素为当前元素
            curr = currNext;
        }
        return resHead;
    }
}
