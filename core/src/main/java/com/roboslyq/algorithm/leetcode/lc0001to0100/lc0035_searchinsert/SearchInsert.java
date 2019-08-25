package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0035_searchinsert;

/**
 * 前提：升序，你可以假设数组中无重复元素。
 * 二分法
 */
public class SearchInsert {
    public static void main(String[] args) {
//        int[] test  = {1,3,5,6};
//        int[] test  = {1,3};
        int[] test  = {2,7,8,9,10};
        SearchInsert searchInsert = new SearchInsert();
        System.out.println( searchInsert.searchInsert(test,9));
    }
    public int res = 0 ;

    public int searchInsert(int[] nums, int target) {
        getIndex(nums,0,nums.length-1,target);
        return res;
    }
    public void getIndex(int[] nums,int startIndex,int endIndex,int target){
        if(endIndex <= startIndex){
            if(target > nums[endIndex]) res = endIndex + 1 ;
            else res = endIndex ;
            return ;
        }
        int middleIndex = (endIndex + startIndex)%2 == 0 ? (endIndex + startIndex)/2 : (endIndex + startIndex)/2 +1;
        int valueMiddle = nums[middleIndex];
        if(valueMiddle == target){
            res = middleIndex;
        }else{
            if (valueMiddle > target){
                getIndex(nums,startIndex,middleIndex - 1,target);
            }else {
                getIndex(nums,middleIndex + 1,endIndex,target);
            }
        }
    }
}

