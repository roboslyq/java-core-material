package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0055_canjump;

public class CanJump {
    public static void main(String[] args) {
        CanJump canJump = new CanJump();
        System.out.println(canJump.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump.canJump(new int[]{3,2,1,0,4}));
    }
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length <=1) return true;
        int resIndex = nums.length - 1;
        int currentIndex = 0;
        boolean res = false;
        while (true){
            int currentValue = nums[currentIndex];
            if(currentValue == 0){
                break;
            }
            currentIndex = currentIndex + currentValue;
            if(currentIndex == resIndex){
                res = true;
                break;
            }
            if(currentIndex >= nums.length ){
                break;
            }
        }
        return res;
    }

    public boolean canJump(int nums[], int currentIndex){
        //TODO
        return true;
    }
}
