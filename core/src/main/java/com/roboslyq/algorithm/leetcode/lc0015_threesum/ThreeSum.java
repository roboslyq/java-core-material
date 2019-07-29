package com.roboslyq.algorithm.leetcode.lc0015_threesum;

import java.util.*;

/**
 * *  *  *  -4,-1,-1, 0, 1, 2,
 */

public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
//        List<List<Integer>> res = threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        List<List<Integer>> res = threeSum.threeSum1(new int[]{-1, 0, 1, 2, -1, -4});
        for(List<Integer>  listInt : res ){
            for(Integer integer : listInt){
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
    public  List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        Arrays.sort(nums); // 排序
        int len = nums.length;
        if(nums == null || len < 3) return ans;
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
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
