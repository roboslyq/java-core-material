package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0057_insert;

import java.util.ArrayList;
import java.util.List;

public class Insert {
    public static void main(String[] args) {
        int[][] test = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInsert = new int[]{4,8};
        Insert insert = new Insert();
        int[][] res = insert.insert(test,newInsert);
        for(int i = 0;i< res.length;i++){
            System.out.println(res[i][0]);
        }
    }
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        if(intervals == null || intervals.length == 0) {
            list.add(newInterval);
            return list.toArray(new int[list.size()][2]);//完成处理
        }
        boolean completed = false;
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            int newLeft = newInterval[0];
            int newRight = newInterval[1];
            if (completed) { //已经完成合并，添加剩余元素到结果中
                list.add(new int[]{left, right});
            } else {
                //情况1：newLeft在左表示需要合并
                if (newLeft <= right) {
                    if(newLeft < left && newRight < left){
                        list.add(new int[]{newLeft,newRight});
                        list.add(new int[]{left, right});
                        completed = true;
                        continue;
                    }else{
                        if (newLeft >= left) {
                            newLeft = left;
                        }
                        //newRight大于right
                        if (newRight > right) {
                            //需要合并
                            while (i + 1 < intervals.length && intervals[i + 1][0] <= newRight) {
                                i++;
                                if (newRight <= intervals[i][1]) {
                                    list.add(new int[]{newLeft, intervals[i][1]});
                                    completed = true;
                                    break;
                                }
                            }
                            //newRight为最大值
                            if (!completed) {
                                list.add(new int[]{newLeft, newRight});
                                completed = true;
                            }
                            continue;
                        } else {
                            newRight = right;
                        }
                        list.add(new int[]{newLeft, newRight});
                        completed = true;
                    }

                } else {//newLeft > right
                    list.add(new int[]{left, right});
                    if (i == intervals.length - 1) {
                        list.add(new int[]{newLeft, newRight});
                    }
                }
            }
        }
        return list.toArray(new int[list.size()][2]);//完成处理
    }

}
