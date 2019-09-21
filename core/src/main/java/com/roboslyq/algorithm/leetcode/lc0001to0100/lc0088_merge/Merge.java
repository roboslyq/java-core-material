package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0088_merge;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Merge {
    public static void main(String[] args) {
        int[] nums1 = {1,2,4,5,6,0};
        int[] nums2 = {3};
        Merge merge =  new Merge();
        merge.merge(nums1,5,nums2,1);
        for(int res : nums1){
            System.out.println(res);
        }
    }
    /**
     * 1、升级遍列，但需要额外的空间保存。可以考虑将nums的元素右移即可。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0) return;
        //因为num1.length 有足够的空间,先将nums1移到最右
        int rigthIndex = nums1.length - m;
        int count = 1;
        for(int i = m-1;i>=0;i--){
            nums1[nums1.length-count++] = nums1[i];
            nums1[i] = 0;
        }
        int resIndex = 0;
        int curNums1 = rigthIndex;
        int curNums2 = 0;
        for (int i = 0; i < m + n; i++) {
            if(curNums1 == nums1.length ){
                nums1[resIndex] =  nums2[curNums2];
                curNums2++;
                resIndex++;
                continue;
            }
            if(curNums2 == nums2.length){
                nums1[resIndex] =  nums1[ curNums1];
                curNums1++;
                resIndex++;
                continue;
            }
            if(nums1[ curNums1] <= nums2[curNums2]){
                nums1[resIndex] =  nums1[ curNums1];
                curNums1++;
            }else{
                nums1[resIndex] =  nums2[curNums2];
                curNums2++;
            }
            resIndex++;
        }
    }
}
