package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0036_isvalidsudoku;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用数据优化Map
 */
public class IsValidSudoku1 {
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
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
//        char[] chars = board[0];
//        System.out.println(board.length);
//        System.out.println(chars.length);
        IsValidSudoku1 isValidSudoku = new IsValidSudoku1();
        System.out.println(isValidSudoku.isValidSudoku(board));

    }

    public boolean isValidSudoku(char[][] board) {
        String[] lineArray = {"", "", "", "", "", "", "", "", ""};
        String[] colunmArray = {"", "", "", "", "", "", "", "", ""};
        ;
        String[] subSudokuArray = {"", "", "", "", "", "", "", "", ""};
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char current = board[i][j];
                int subSudokuIndex = (i / 3) * 3 + j / 3;
                ;
                if (current == '.') {
                    continue;
                }
                //处理行
                if (lineArray[i].indexOf(current) >= 0
                        || colunmArray[j].indexOf(current) >= 0
                        || subSudokuArray[subSudokuIndex].indexOf(current) >= 0
                ) {
                    return false;
                } else {
                    lineArray[i] = lineArray[i] + current;
                    colunmArray[j] = colunmArray[j] + current;
                    subSudokuArray[subSudokuIndex] = subSudokuArray[subSudokuIndex] + current;
                }
            }
        }
        return true;
    }
}
