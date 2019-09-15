package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0062_uniquepaths;

/**
 * 思路二：动态规划
 *
 * 我们令 dp[i][j] 是到达 i, j 最多路径
 *
 * 动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
 *
 * 注意，对于第一行 dp[0][j]，或者第一列 dp[i][0]，由于都是在边界，所以只能为 1
 *
 * 时间复杂度：O(m*n)O(m∗n)
 *
 * 空间复杂度：O(m * n)O(m∗n)
 *
 * 优化：因为我们每次只需要 dp[i-1][j],dp[i][j-1]
 *
 * 所以我们只要记录这两个数，直接看代码吧！
 *
 * 作者：powcai
 * 链接：https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-by-powcai-2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class UniquePaths {
    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3,2));
        System.out.println(uniquePaths.uniquePaths(7,3));
    }
    public int uniquePaths(int m, int n) {
        int[][] nums = new int[m][n];
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(i == 0) nums[0][j] = 1;
                else if(j == 0) nums[i][0] = 1;
                else nums[i][j] =  nums[i-1][j] + nums[i][j-1];
            }
        }
        return nums[m-1][n-1];
    }
}
