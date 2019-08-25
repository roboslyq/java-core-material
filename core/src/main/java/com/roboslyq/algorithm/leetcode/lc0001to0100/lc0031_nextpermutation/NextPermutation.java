package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0031_nextpermutation;

/**
 * 单向指针循环法：
 * 1、从高位往低位遍列，并且保证高位升序（升序值最小）
 * 2、当前位的值，与高位所有的值从错到高比较，如果发生高位值有大于当前位的，则结束比较，交换位置即为最终结果。
 *    如果没有，则将高位所有位置前移一位，最高位保存当前位值。
 */
public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();
//        int[] test1 = new int[]{1,3,2};
        int[] test1 = new int[]{2,3,1};
        int[] test3 = new int[]{1,1,5};

        nextPermutation.nextPermutation(test1);
//        nextPermutation.nextPermutation(test2);
//        nextPermutation.nextPermutation(test3);
        print(test1);
//        print(test2);
//        print(test3);

    }
    public static  void print(int[] print){
        for(int i :print){
            System.out.print(i +",");
        }
        System.out.println();
    }
    public void nextPermutation(int[] nums) {
        boolean finishFlag = false;//结束标识，提前判断结束 ，终止循环
        for(int i = nums.length -1 ;i>=0;i--){//从高位往低位
            if (!finishFlag){
                int currentValue = nums[i];
                for(int j = i+1;j<=nums.length - 1;j++){
                    if(currentValue < nums[j]) {//有符合条件的记录，交换数据并且结循环
                        int tmp = nums[j];
                        nums[i] = tmp;
                        nums[j] = currentValue;
                        finishFlag = true;
                        break;
                    }
                }
                if(!finishFlag){
                    //没有符合条件的记录，交换位置，保证后面的序列为升序。放前移一位，最高位存储currentValue
                    for (int k = i ; k< nums.length-1; k++){
                        nums[k] = nums[k+1];
                    }
                    //当前值最大，如果不是最大则上面for循环成功，finishFlag = true，不会进入此循环
                    nums[nums.length-1] = currentValue;
                }
            }
        }
    }
}
