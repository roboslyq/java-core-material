package com.roboslyq.algorithm.leetcode.lc0002_addtwonumbers;

/**
 * 求单向列表相加：
 * 从左向右循环，注意进位标识
 * https://leetcode-cn.com/problems/add-two-numbers/submissions/
 */
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
        ListNode l2next3 = new ListNode(5);
        l2.next = l2next1;
        l2next1.next = l2next2;
        l1next2.next = l2next3;
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
        //创建结果ListNode，结果从ListNode.next开始
        ListNode res = new ListNode(0);
        //进位标识
        boolean carryFlag = false;
        //临时结果存储
        int tmp ;
        //结果当前ListNode
        ListNode resCurrent = res;
        while (l1 != null || l2 != null || carryFlag){ // l1,l2不为空，或者l1,l2为空但最后有进位的情况
            ListNode resNext = new ListNode(0);//当前新的结果
           if(l1 != null && l2 != null){ //计算l1和l2不为空
               tmp = carryFlag ?  l1.val + l2.val + 1 : l1.val + l2.val;
               carryFlag = tmp > 9;
               resNext.val = tmp%10;
           }else if(l1 != null){ //计算l1不为空但l2为空情况
               tmp = carryFlag ?  l1.val + 1 : l1.val;
               carryFlag = tmp>9;
               resNext.val = tmp%10;
           }else  if(l2 != null){ //计算l2不为空，l1为空情况
               tmp = carryFlag ?  l2.val + 1 : l2.val;
               carryFlag = tmp>9;
               resNext.val = tmp%10;
           }else {//两者都为空并且有进位待处理
               resNext.val = 1;//处理进位
               carryFlag = false ;//下一次没有进位，结束处理
           }
           //将当前的结果res.next中（res->resCurrent->resCurrent->.....）
           resCurrent.next = resNext;
           //设置新的结果为当前结果
           resCurrent = resCurrent.next;
           //前进到下一节点
            if(l1 != null)  l1 = l1.next;
            if(l2 != null)  l2 = l2.next;
        }
        return res.next;
    }
}
