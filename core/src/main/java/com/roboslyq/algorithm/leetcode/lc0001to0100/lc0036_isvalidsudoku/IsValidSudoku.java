package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0036_isvalidsudoku;

import java.util.HashMap;
import java.util.Map;

public class IsValidSudoku {
    public static void main(String[] args) {
//        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
//  {'6','.','.','1','9','5','.','.','.'},
//  {'.','9','8','.','.','.','.','6','.'},
//  {'8','.','.','.','6','.','.','.','3'},
//  {'4','.','.','8','.','3','.','.','1'},
//  {'7','.','.','.','2','.','.','.','6'},
//  {'.','6','.','.','.','.','2','8','.'},
//  {'.','.','.','4','1','9','.','.','5'},
//  {'.','.','.','.','8','.','.','7','9'}
//};
    char[][] board = {
         {'8','3','.','.','7','.','.','.','.'}
        ,{'6','.','.','1','9','5','.','.','.'}
        ,{'.','9','8','.','.','.','.','6','.'}
        ,{'8','.','.','.','6','.','.','.','3'}
        ,{'4','.','.','8','.','3','.','.','1'}
        ,{'7','.','.','.','2','.','.','.','6'}
        ,{'.','6','.','.','.','.','2','8','.'}
        ,{'.','.','.','4','1','9','.','.','5'}
        ,{'.','.','.','.','8','.','.','7','9'}
};
//        char[] chars = board[0];
//        System.out.println(board.length);
//        System.out.println(chars.length);
        IsValidSudoku isValidSudoku = new IsValidSudoku();
        System.out.println(isValidSudoku.isValidSudoku(board));

    }
    public boolean isValidSudoku(char[][] board) {
        Map<Integer,String> lineMap = new HashMap<>();
        Map<Integer,String> colunmMap = new HashMap<>();
        Map<Integer,String> subSudokuMap = new HashMap<>();
        for(int i =0 ;i<9;i++){
            lineMap.put(i,"");
            colunmMap.put(i,"");
            subSudokuMap.put(i,"");
        }
       for(int i = 0;i<9;i++){
           for(int j = 0 ; j < 9 ; j++){
               char current = board[i][j];
                String lineTmp = lineMap.get(i);
                if(current == '.'){
                    continue;
                }
                //处理行
                if(lineTmp.indexOf(current) >= 0){
                    return false;
                }else {
                    lineMap.put(i,lineTmp + current);
                }
                //处理列
               String colunmTmp = colunmMap.get(j);
               if(colunmTmp.indexOf(current) >= 0){
                   return false;
               }else {
                   colunmMap.put(j,colunmTmp + current);
               }
               int subSudokuIndex = getSubSudokuIndex( i,j);
               String subSudokuTmp = subSudokuMap.get(subSudokuIndex);
               if(subSudokuTmp.indexOf(current) >= 0){
                   return false;
               }else {
                   subSudokuMap.put(subSudokuIndex,subSudokuTmp + current);
               }
           }
       }
        return true;
    }
    public int getSubSudokuIndex(int line,int  colunm){
        return (line / 3) * 3 + colunm / 3;
    }
}
