package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0055_canjump;

/**
 * 特点：
 * 1、不能跳过的点的值为0（没有下一步），所以从后(倒数第2个元素)往前找，遇到0(比如位置为j)则判断，前面的
 *    元素能否跳过这个0。能否跳过这个0的规则是：第i个元素中的步骤v,要大于(j-i)差值，则表明可以
 *    跳过这个0元素，否则不能。
 */
public class CanJump1 {
    public static void main(String[] args) {
        CanJump1 canJump = new CanJump1();
        System.out.println(canJump.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump.canJump(new int[]{3,2,1,0,4}));
    }

    public boolean canJump(int[] nums) {
        int j = nums.length - 2;
        int zeroIndex = -1;// -1表示不存在0元素，其它值表示0元素的位置
        while (j >= 0) {
            int valueCur = nums[j];
            // 如果当前元素为0，并且zeroIndex 为-1，则设置zeroIndex为当前元素位置
            if (valueCur == 0 && zeroIndex == -1) {
                zeroIndex = j;
            }
            //如果zeroIndex 不为-1，则表示需要跳过这个元素，条件是valueCur > zeroIndex - j
            if (zeroIndex != -1 && valueCur > zeroIndex - j) {
                //跳过后，重置zeroIndex位置为-1
                    zeroIndex = -1;
            }
            //指针继续向左移动
            j--;
        }
        return zeroIndex == -1 ? true : false;
    }
}
