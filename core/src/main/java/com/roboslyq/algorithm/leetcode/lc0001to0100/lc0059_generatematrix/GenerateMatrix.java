package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0059_generatematrix;

public class GenerateMatrix {
    public static void main(String[] args) {
        GenerateMatrix generateMatrix = new GenerateMatrix();
        generateMatrix.generateMatrix(3);
    }
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int y = n;
        int x = n;
        int count = x * y;
        int c_h = 0; //high,上方高度的初始值，即第0行开始
        int c_d = y-1;//down,下方的高度值，从数据
        int c_r = x -1;
        int c_l = 0;
        int cur_x = 0;//x轴值（行数）
        int cur_y = 0;//y轴值（列数）
        int for_type = 0; // 0 : 行右，1：上下 2 行左 3 下上
        int currentValue = 0;
        while (currentValue++ <= count){
            matrix[cur_x][cur_y] = currentValue;
            //当currentValue == count，表示循环结束
            if(currentValue == count){
                break;
            }
            switch (for_type){
                case 0:
                    cur_y++;
                    if(cur_y == c_r){
                        c_h++;
                        for_type=1;
                    }
                    break;
                case 1:
                    cur_x++;
                    if(cur_x == c_d){
                        c_r--;
                        for_type=2;
                    }
                    break;
                case 2:
                    cur_y--;
                    if(cur_y == c_l){
                        c_d--;
                        for_type=3;
                    }
                    break;
                case 3:
                    cur_x--;
                    if(cur_x == c_h){
                        c_l++;
                        for_type = 0;
                    }
                    break;
            }
        }
        return matrix;
    }
}
