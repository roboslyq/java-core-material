package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0056_merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Merge {
    public static void main(String[] args) {
//        int[][] test = new int[3][2];
//        test[0][0] = 3;
//        test[1][0] = 2;
//        test[2][0] = 1;
//        Arrays.sort(test,Comparator.comparing(o -> o[0]));
//        for(int i = 0;i< test.length;i++){
//            System.out.println(test[i][0]);
//        }
        int[][] test = {{1,3},{2,6},{8,10},{15,8}};
        Merge merge = new Merge();
       int[][] res = merge.merge(test);
       for(int i = 0;i< res.length;i++){
            System.out.println(res[i][0]);
        }
    }
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0 ) return new int[0][0];
        // 性能差别大
 //       Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b){
                return a[0]-b[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i<intervals.length;i++){
            int left = intervals[i][0];
            int right = intervals[i][1];
            while ( i+1 < intervals.length && intervals[i+1][0] <= right){
                if(right < intervals[i+1][1]){
                    right = intervals[i+1][1];
                }
                i++;
            }
            list.add(new int[]{left,right});
        }
//        return list.toArray(new int[0][]);
        return list.toArray(new int[list.size()][2]);
    }


}
