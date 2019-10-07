/**
 * Copyright (C), 2015-2019
 * FileName: SortList
 * Author:   luo.yongqian
 * Date:     2019/9/29 17:52
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/9/29 17:52      1.0.0               创建
 */
package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0148_sortlist;

import com.roboslyq.algorithm.leetcode.ListNode;
import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * 〈在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。〉
 * @author luo.yongqian
 * @create 2019/9/29
 * @since 1.0.0
 */
public class SortList {
    public static void main(String[] args) {
        SortList sortList = new SortList();
        ListNode l1= new ListNode(4);
        ListNode l2= new ListNode(2);
        ListNode l3= new ListNode(1);
        ListNode l4= new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        ListNode res = sortList.sortList(l1);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }

    /**
     * 先将ListNode转换成Array，然后借用Java中Array已经存在的数组排序，最后拼接为ListNode返回即可。
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        ArrayList<ListNode> array = new ArrayList<>();
        while(head!=null){
            array.add(head);
            head = head.next;
        }
        array.sort(Comparator.comparingInt(a -> a.val));
        ListNode resHead = new ListNode(0);
        ListNode tmpHead = resHead;
        for (int i = 0; i < array.size(); i++) {
            ListNode node = array.get(i);
            tmpHead.next = node;
            tmpHead = node;
            //删除末尾节点的Next,否则可能形成致循环链表
            if(i == array.size()-1){
                tmpHead.next = null;
            }
        }
        return resHead.next;
    }

    /**
     * 归并排序（注意cut和merge链操作即可）
     * @param head
     * @return
     */
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }
}