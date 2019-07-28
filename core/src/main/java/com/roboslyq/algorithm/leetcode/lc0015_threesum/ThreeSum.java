package com.roboslyq.algorithm.leetcode.lc0015_threesum;

import java.util.*;

/**
 * *  *  *  -4,-1,-1, 0, 1, 2,
 */

public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> res = threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for(List<Integer>  listInt : res ){
            for(Integer integer : listInt){
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        //特殊情况处理
        if(nums[0] > 0 || nums[nums.length -1] < 0 ){
            return null;
        }
        //a|b|c 作为Key，用来判重,且a<=b<=c
        Map<String,Object> resultKey = new HashMap<>();
        //两边向中间寻找（left ,right是以0为中心的左或者右，可以一个数组中没有0）
        for(int i=0;i<nums.length;i++){
            int currentLeftIndex = i;
            int currentRightIndex = nums.length - 1;
            int left = nums[currentLeftIndex];
            if(currentLeftIndex>=1 && nums[currentLeftIndex] == nums[currentLeftIndex- 1]){
                currentLeftIndex++;
                continue;
            }
            if(left >0 || nums[currentRightIndex] <0 ){//结束判断
               break;
            }
            while(currentRightIndex > currentLeftIndex){
                int right = nums[currentRightIndex];
                if(left + right <= 0){//负数较大，需要一个正数来补充，从右往左找
                    for(int j = currentRightIndex -1;j >= i;j-- ){
                        int right2 = nums[j];
                        if(right2 < 0) {
                            break;
                        }
                        if(left + right + right2 == 0){
                            String key = left +"|" + right2 + "|" + right;
                            if(!resultKey.containsKey(key)){
                                resultKey.put(key,"");
                                resultList.add(Arrays.asList(left,right2,right));
                            }
                            currentRightIndex--;
                            break;
                        }else if(left + right + right2 < 0){
                            break;
                        }

                    }
                    currentRightIndex--;
                    if(nums[currentRightIndex] <0 ){
                        break;
                    }
                }else{
                    for(int j = currentLeftIndex + 1;nums[currentLeftIndex] >0 ;j++ ){
                        int left2 = nums[j];
                        if(left + right + left2 == 0){
                            String key = left +"|" + left2 + "|" + right;
                            if(!resultKey.containsKey(key)){
                                resultKey.put(key,"");
                                resultList.add(Arrays.asList(left,left2,right));
                            }
                            currentLeftIndex ++ ;
                            break;
                        }else if(left + right + left2 > 0){
                            break;
                        }
                    }
                    currentLeftIndex++;
                    if(nums[currentLeftIndex] >0 ){
                        break;
                    }
                }
            }
        }
        return  resultList;
    }
}
