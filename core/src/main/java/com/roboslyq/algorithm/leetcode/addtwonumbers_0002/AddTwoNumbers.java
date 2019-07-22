package com.roboslyq.algorithm.leetcode.addtwonumbers_0002;

public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);
        ListNode l1next1 = new ListNode(4);
        ListNode l1next2 = new ListNode(4);
        l1.next = l1next1;
        l1next1.next = l1next2;
        ListNode l2 = new ListNode(5);
        ListNode l2next1 = new ListNode(6);
        ListNode l2next2 = new ListNode(4);
        l2.next = l2next1;
        l2next1.next = l2next2;
        ListNode res = addTwoNumbers.addTwoNumbers(l1,l2);
        ListNode tmp = res.next;
        while (tmp != null){
            System.out.println(tmp.val);
            tmp = tmp.next;
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null ){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        ListNode res = new ListNode(0);
        boolean carryFlag = l1.val + l2.val > 9;
        res.val =( l1.val + l2.val) % 10;
        ListNode resCurrent = res;
        int tmp = 0;
        while (true){
            ListNode l1Next = l1.next;
            ListNode l2Next = l2.next;
            ListNode resNext = new ListNode(0);
           if(l1Next != null && l2Next != null){
               tmp = carryFlag ?  l1.val + l2.val + 1 : l1.val + l2.val;
               carryFlag = tmp > 9;
               resNext.val = tmp%10;
           }else if(l1Next != null){
               tmp = carryFlag ?  l1.val + 1 : l1.val;
               carryFlag = tmp>9;
               resNext.val = tmp%10;
           }else if(l2Next != null){
               tmp = carryFlag ?  l2.val + 1 : l2.val;
               carryFlag = tmp>9;
               resNext.val = tmp%10;
           }else{
                break;
           }
           resCurrent.next = resNext;
           resCurrent = resNext;
            l1 = l1.next;
            l2 = l2.next;
        }
        return res;
    }
}
