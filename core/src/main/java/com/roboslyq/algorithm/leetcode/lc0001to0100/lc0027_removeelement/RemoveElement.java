package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0027_removeelement;

public class RemoveElement {
    public static void main(String[] args) {

    }
    public int removeElement(int[] nums, int val) {
        //记录当前不等于目标val的位置，如果遇到下一个元素不等于val，则将元素存放在此位置，并且空位置加1
        int nextEmpty = 0;
        for (int i = 0;i<nums.length;i++){
            int valAtI = nums[i];
            if(valAtI != val){
                nums[nextEmpty] = valAtI ;
                nextEmpty++;
            }
        }
        return nextEmpty + 1;
    }
}
