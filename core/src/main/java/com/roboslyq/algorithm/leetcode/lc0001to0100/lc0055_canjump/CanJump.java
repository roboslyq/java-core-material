package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0055_canjump;

/**
 * 回溯思想：此方法需要枚举所有的情况，用时较多。
 */
public class CanJump {
    public static void main(String[] args) {
        CanJump canJump = new CanJump();
        System.out.println(canJump.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump.canJump(new int[]{3,2,1,0,4}));
    }

    public boolean canJump(int[] nums) {
        return canJumpFromPosition(0,nums);
    }

    public boolean canJumpFromPosition(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                return true;
            }
        }
        return false;
    }


}
