package com.roboslyq.algorithm.leetcode.maxarea_0011;

/**
 * 此方法我也不知道叫什么名字，暂叫“水位下移法”吧。
 * 即先找到数组中，数字最大什(o(n)复杂度)，然后水位线往下移，分别找最右边和最右边大于或等于水位线的值。
 * 此时面积为当前水位线面积最大值。
 * 循环查找直到水位线等于0.
 */
public class MaxArea {
    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        System.out.println(maxArea.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
    public int maxArea(int[] height) {
        int max_y = 0;
        int max_container = 0;
        //获取最高值
        for (int value : height) {
            max_y = max_y < value ? value : max_y;
        }
        for(int i = max_y;  i>0; i--){//水位线下移
            int left = 0;
            int right = 0;
            for(int j = 0;j<height.length;j++) {//找当前水位线最左和最右，求面积
                if(height[j] >= max_y){
                    left = j;
                    break;
                }
            }
            for(int j = height.length - 1;j >=0 ;j--){
                if(height[j] >= max_y){
                    right = j;
                    break;
                }
            }
            int current_max_container = (right - left) * max_y;
            if( current_max_container >  max_container){
                max_container  = current_max_container;
            }
            max_y--;
        }
        return max_container;
    }
}

