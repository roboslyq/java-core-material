package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0064_minpathsum;

/**
 * 借用第62/63题的回溯思想：
 * 自顶向下计算方法：每次只能向右或者向下走，分析其中一个点，
 * 在经过第一列的每个点D(i,0)时：必然经过点D(i-1,0),那么第一列的状态转移方程为：f(i,0)=f(i-1,0)+D(i,0)
 * 在经过第一行的每个点D(0,j)时：必然经过点D(0,j-1),那么第一行的状态转移方程为：f(0,j)=f(0,j-1)+D(0,j)
 * 在经过非边界点D(i,j)时，必然经过具有min(f(i,j-1),f(i-1,j))的点，那么非边界点的状态转移方程为：
 * f(i,j)=min(f(i,j-1),f(i-1,j))+D(i,j);
 */
public class MinPathSum {
    public static void main(String[] args) {

    }
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //处理行第一行：边界行
        for(int j = 1; j< n;j++){
            grid[0][j] = grid[0][j] + grid[0][j-1];
        }
        //处理行第一列：边界行
        for(int i = 1; i< m;i++){
            grid[i][0] = grid[i][0] + grid[i-1][0];
        }
        //正常处理
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                int minPre = grid[i-1][j] > grid[i][j-1] ? grid[i][j-1] :  grid[i-1][j] ;
                grid[i][j] = grid[i][j] + minPre;
            }
        }
        return grid[m-1][n-1];
    }
}
