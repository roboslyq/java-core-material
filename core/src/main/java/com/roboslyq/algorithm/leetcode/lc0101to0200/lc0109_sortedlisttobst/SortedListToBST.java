package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0109_sortedlisttobst;

import com.roboslyq.algorithm.leetcode.ListNode;
import com.roboslyq.algorithm.leetcode.TreeNode;

import java.util.ArrayList;

public class SortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        ArrayList<ListNode> arrayList = new ArrayList<>();
        while(head != null){
            arrayList.add(head);
            head = head.next;
        }
        return generateBST(arrayList,0,arrayList.size()-1);
    }

    /**
     * 这个方法是空间换时间的经典案例。
     *
     * 你可以通过使用更多空间来降低时间复杂度。
     *
     * 在这个方法中，我们将给定的链表转成数组并利用数组来构建二叉搜索树。数组找中间元素只需要 O(1)O(1) 的时间，所以会降低整个算法的时间复杂度开销。
     *
     * 算法
     *
     * 1、将给定链表转成数组，将数组的头和尾记成 left 和 right 。
     * 2、找到中间元素 (left + right) / 2，记为 mid。这需要 O(1)O(1) 时间开销，也是与上面算法主要改进的地方。
     * 3、将中间元素作为二叉搜索树的根。
     * 4、递归构造二叉搜索树的左右两棵子树，两个子数组分别是 (left, mid - 1) 和 (mid + 1, right)。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/solution/you-xu-lian-biao-zhuan-huan-er-cha-sou-suo-shu-by-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param arrayList
     * @param leftNodeIndex
     * @param rightNodeIndex
     * @return
     */
    public TreeNode generateBST(ArrayList<ListNode> arrayList,int leftNodeIndex,int rightNodeIndex){
        if(leftNodeIndex > rightNodeIndex) return null;
        int middleIndex = (rightNodeIndex + leftNodeIndex)/2;
        //每次生成middleIndex位置的节点
        TreeNode curRoot = new TreeNode(arrayList.get(middleIndex).val);
        //左右分别排除middleIndex即可，递归生成左子树和右子树
        curRoot.left = generateBST(arrayList,leftNodeIndex,middleIndex-1);
        curRoot.right = generateBST(arrayList,middleIndex+1,rightNodeIndex);
        return curRoot;
    }
}
