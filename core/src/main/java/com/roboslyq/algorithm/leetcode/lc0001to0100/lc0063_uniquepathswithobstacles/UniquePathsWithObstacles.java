package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0063_uniquepathswithobstacles;

/**
 * 借用62题的算法：dp[i][j] = dp[i-1][j] + dp[i][j-1]
 *  如果dp[i-1][j] 是障碍，将其值设置为0即可（即对路径的贡献值为0）
 */
public class UniquePathsWithObstacles {
    public static void main(String[] args) {

    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1){//特殊情况，第一个点是障碍物，不能到达
            return 0;
        }
        obstacleGrid[0][0] = 1;
        //处理行第一行：边界行
        boolean hasObstacle  = false;
        for(int j = 1; j< n;j++){
            if(obstacleGrid[0][j] == 1){
                hasObstacle = true;
            }
            if(!hasObstacle){
                obstacleGrid[0][j] = 1;
            }else {
                obstacleGrid[0][j] = 0;
            }
        }
        //处理行第一列：边界行
        hasObstacle  = false;
        for(int i = 1; i< m;i++){
            if(obstacleGrid[i][0] == 1){
                hasObstacle = true;
            }
            if(!hasObstacle){
                obstacleGrid[i][0] = 1;
            }else {
                obstacleGrid[i][0] = 0;
            }
        }
        //正常处理
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                if(obstacleGrid[i][j] == 0){//正常节点公式为dp[i][j] = dp[i-1][j] + dp[i][j-1]
                    obstacleGrid[i][j] =  obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                }else {
                    obstacleGrid[i][j] = 0;//障碍物节点不可到达，为0
                }
            }
        }

        return obstacleGrid[m-1][n-1];
    }
}
