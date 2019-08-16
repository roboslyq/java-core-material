/**
 * Copyright (C), 2015-2019
 * FileName: RemoveNthFromEnd
 * Author:   luo.yongqian
 * Date:     2019/8/1 10:18
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/8/1 10:18      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0019_removenthfromend;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/8/1
 * @since 1.0.0
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {
        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        removeNthFromEnd.removeNthFromEnd(new ListNode(1),1);

    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //使用Map保存每个ListNode元素，map的key为ListNode所在链表的位置（ListNode没有重新new，只是新建了引用，因此存储空间不变）
        Map<Integer,ListNode> iListNode = new HashMap<>();
        ListNode tmp = head;
        //将ListNode转为Map
        int i = 0;
        while(tmp != null){
            iListNode.put(i,tmp);
            tmp = tmp.next;
            i++;
        }
        //计算删除位置前一位置
        int index = i - n - 1;
        if(index == -1){//如果是删除首元素，直接将head指向head.next即可。
            head = head.next;
        }else{//否则，先获取删除位置的Pre元素，然后将pre元素的next指向pre.next.next即可
            ListNode preDel = iListNode.get(index);
            if(preDel.next != null){
                preDel.next = preDel.next.next;
            }
        }
       return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}