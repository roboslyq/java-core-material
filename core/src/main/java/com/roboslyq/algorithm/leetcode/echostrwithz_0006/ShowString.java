package com.roboslyq.algorithm.leetcode.echostrwithz_0006;

/**
 * 可用，但算法效率不高
 */
public class ShowString {
    public static void main(String[] args) {
        ShowString ss = new ShowString();
        System.out.println(ss.showString("abcd",4));
        System.out.println(ss.showString("abcd",3));
        System.out.println( ss.showString("ctcjqmrkiwhwerepqyehsyirqvxryrwbmbmepfpzeyvkrzajzesteakwvhnwalznmnrbuicygxjxylixrbtvbdrzngxnrwcglujfcmellpkmctltueqvkjuxprmippoajyinmmyxdjjfevayzqtlzqiojxybmndxllmxzlwcwgjadjaebvqalaqxxpyjedippvooimtgucixoierfwsrwkqubqfftqwinmxvzsdtwltmvxeatytrillkbtpvlofyaetzwyttlofiljkghexspletgvqrjvpkakjyietvszdfknlutlojyseenuxxpohrysqixldpkivxvitpvhatbezoawnpkwjkpbummzdzhayflrugawcbabrayhrkdcxsdrgsrmramp",
            170));
    }
    public String showString(String s,int numRows){
        //特殊情况处理
        if(numRows == 1 || s == null || s.length() <= numRows ){
            return s;
        }

        //n + (n-2)为一个单位。n
        int len = s.length();
        int binaryArraySize = getBinarySize(numRows,len);
        char[][] binaryArray= new char[numRows][binaryArraySize];
        init(binaryArray);
        //循环遍列二维数组的行
        int x=0;
        int y=0;
        boolean modeAdd = true;
        for(int i = 0; i< s.length();i++){
            if(modeAdd){
                binaryArray[x][y] = s.charAt(i);
                if(x == numRows-1){
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
        return print(binaryArray);
    }
    /**
     * @param n 二维数据行数
     * @param len 字符串长度
     * @return 二维数据列数
     */
    public int getBinarySize(int n ,int len){
        int tmp = len % ( 2*n - 2) ;
        int integerUnit = (len / ( 2*n - 2));//整数个单元长度
        if(tmp == 0){
            return integerUnit*(n-1);
        }else if(tmp <= n){
            return  integerUnit*(n-1) + 1;
        }else {
            return  integerUnit*(n-1) + (tmp - n) + 1;
        }
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
               // System.out.print(array[i][j]);
                if(array[i][j] != ' '){
                    sb.append(array[i][j]);
                }
            }
            //System.out.print("\r\n");
        }
        return sb.toString();
    }


}
