package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0033_search;

public class Search {
    public static void main(String[] args) {
        int[] test  = {4,5,6,7,8,9,1,2,3};
//        int[] test  = {3,1};
        Search searchRange = new Search();
//        int  res = searchRange.search(test,1);
//        System.out.println(res);
        int  res1 = searchRange.search(test,1);
        System.out.println(res1);
    }
    public int search(int[] nums, int target) {
        int result = -1;
        if (null == nums || nums.length == 0) {
            return result;
        }
//        if(nums.length == 1){
//            return nums[0] == target ? 0 : -1;
//        }
//        if(nums.length == 2){
//            return nums[0] == target || nums[1] == target ? 0 : -1;
//        }
        int left = 0;
        int right = nums.length -1;
        int reverseIndex = 0;

        while(left < right){
            int middleIndex = (right + left) % 2 == 0 ? (right + left) / 2 : (right + left) / 2  + 1;
            if(middleIndex == 0){
                reverseIndex = 0;
                break;
            }
            if(middleIndex == nums.length -1){
                reverseIndex = nums[middleIndex - 1] < nums[middleIndex] ? middleIndex-1: middleIndex;
                break;
            }
            int middleValue = nums[middleIndex];
            int leftValue = nums[middleIndex -1];
            int rightValue = nums[middleIndex + 1];
            if(middleValue < rightValue && middleValue < leftValue){
                reverseIndex = middleIndex;
                break;
            }else if(middleValue < leftValue && middleValue > rightValue ){//降序，表明翻转点在右侧
                left = middleIndex + 1;
            }else if (middleValue > leftValue && middleValue < rightValue)
            {//升序,翻转点在左侧
                right = middleIndex -1;
            }else{//middleValue为最大值，表明 middleIndex + 1即为翻转点
                reverseIndex = middleIndex + 1;
                break;
            }
        }
        int valueReverse = nums[reverseIndex];
        if(valueReverse == target){
            result =  reverseIndex;
        }
        else if(valueReverse > target){
            // result = -1;
        }else{
            result = searchResule(nums,0,reverseIndex - 1,true,target);
            if (result == -1) {
                result = searchResule(nums,reverseIndex+1,nums.length -1,true,target);
            }
        }

        return  result;
    }


    public int searchResule(int[] nums,int left,int right,boolean ascFlag, int target) {
        int res = -1;
        while (left <= right) {
            //取中值
            int middleIndex = (right + left ) % 2 == 0 ? (right + left ) / 2 : (right + left ) / 2 + 1;
            //分支1 ： 中值 = 目标值
            if (nums[middleIndex] == target) {
                res = middleIndex;
                break;//找到值后，中断循环
            }
            if(ascFlag){//升序
                //分支2 ： 中值 < 目标值，表示目标值可能落在右侧，修改left为中值即可。
                if (nums[middleIndex] < target) {
                    left = middleIndex + 1;
                }
                //分支3 ： 中值 > 目标值，表示目标值可能落在右侧，修改right为中值即可。
                if (nums[middleIndex] > target) {
                    right = middleIndex - 1;
                }
            }else{//降序
                //分支2 ： 中值 < 目标值，表示目标值可能落在右侧，修改left为中值即可。
                if (nums[middleIndex] < target) {
                    right = middleIndex - 1;
                }
                //分支3 ： 中值 > 目标值，表示目标值可能落在右侧，修改right为中值即可。
                if (nums[middleIndex] > target) {
                    left = middleIndex + 1;
                }
            }


        }
        return res;
    }
}
