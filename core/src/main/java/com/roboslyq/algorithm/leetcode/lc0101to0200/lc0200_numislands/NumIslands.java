package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0200_numislands;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * 输出: 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumIslands {
    public static void main(String[] args) {
        NumIslands numislands = new NumIslands();
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        int numIslands1 = numislands.numIslands(grid1);
        System.out.println(numIslands1);

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int numIslands2 = numislands.numIslands(grid2);
        System.out.println(numIslands2);


    }

    /**
     * DSF(depth search firstly):深度优先遍列算法
     * 1、分右下左上四个方向遍列，在外层循环如果遇到1，则count加1
     * 2、在扩散函数中，遇到1则继续递归扩散，并且可以通过改变原数据的值为9表示已经遍列过了。
     * 当然，可以添加额外的一个对应二维boolean数组，记录是否已经遍列过。
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;
        int lineNum = grid.length;
        int columnNum = grid[0].length;
        int count = 0;
        for(int i = 0;i<lineNum;i++){
            for (int j = 0; j < columnNum; j++) {
                char curVal = grid[i][j];
                if(curVal == '1'){
                    count++;
                    spread(grid,i,j);
                }
            }
        }
        return count;
    }

    /**
     * 扩散函数：顺时针扩展，即右下左上
     * 主要是标记同一岛屿的值为已经遍列过了
     * @param grid
     * @param x
     * @param y
     */
    public void spread(char[][] grid,int x,int y){
        int lineNum = grid.length;
        int columnNum = grid[0].length;
        //当前节点为1，则继递归扩散，否则返回
        if(grid[x][y] == '1'){
            //修改值为9，标识已经遍列过了
            grid[x][y] = '9';
            //递归右扩散
            if(y < columnNum-1){
                spread(grid,x,y+1);
            }
            //递归下扩散
            if(x < lineNum -1){
                spread(grid,x+1,y);
            }
            //递归左扩散
            if(y > 0){
                spread(grid,x,y-1);
            }
            //递归上扩散
            if(x >0 ){
                spread(grid,x-1,y);
            }
        }
    }
}
