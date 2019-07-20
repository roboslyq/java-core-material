package com.roboslyq.algorithm.leetcode;

public class ShowString {
    public static void main(String[] args) {
        ShowString ss = new ShowString();
//     ss.showString("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz",4);
     ss.showString("PAYPALISHIRING",4);
    }
    public String showString(String s,int n){
        //n + (n-2)为一个单位。n
        int len = s.length();
        int binaryArraySize = getBinarySize(n,len);
        char[][] binaryArray= new char[n][binaryArraySize];
        init(binaryArray);
        //循环遍列二维数组的行
        int x=0;
        int y=0;
        boolean modeAdd = true;
        for(int i = 0; i< s.length();i++){
            if(modeAdd){
                binaryArray[x][y] = s.charAt(i);
                if(x == n-1){
                    modeAdd = false;
                    x--;
                    y++;
                    continue;
                }
                x++;
            }else{
                binaryArray[x][y] = s.charAt(i);
                if(x == 0){
                    modeAdd = true;
                    x++;
                    continue;
                }
                x--;
                y++;

            }

        }
        System.out.println( print(binaryArray));
        return "";
    }
    public void init(char[][] array){
        for(int i = 0;i< array.length;i++){
            for(int j=0;j<array[i].length;j++){
               array[i][j] = ' ';
            }
        }
    }
    public String print(char[][] array){
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i< array.length;i++){
            for(int j=0;j<array[i].length;j++){
                System.out.print(array[i][j]);
                if(array[i][j] != ' '){
                    sb.append(array[i][j]);
                }
            }
            System.out.print("\r\n");
        }
        return sb.toString();
    }
    /**
     * @param n 二维数据行数
     * @param len 字符串长度
     * @return 二维数据列数
     */
    public int getBinarySize(int n ,int len){
       int tmp = len % ( 2*n - 2) ;
       int integerUnit = (len / ( 2*n - 2))*(n-1);//整数个单元长度
       if(tmp == 0){
           return integerUnit;
       }else if(tmp <= n){
           return  integerUnit + 1;
       }else {
           return  integerUnit + (tmp - n) + 1;
       }
    }

}
