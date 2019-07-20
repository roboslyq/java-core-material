package com.roboslyq.algorithm.leetcode;

public class ShowString1 {
    public static void main(String[] args) {
        ShowString1 ss = new ShowString1();

    }
    public String showString(String s,int n){
        StringBuffer res = new StringBuffer();
        int maxJ = getJ(s.length(),n);
        //把结果想像成二维数组，i为二维数组y值。j为x轴值
        for(int i = 0; i < n; i++) {
            int count = 0; //当前行处理j值的次数
            int currentIndex = 0;

            while (true) {
                int unit = count % ( n - 1);
                if( unit == 0){
                    res.append(s.charAt(i));//第一列的值
                }

                currentIndex = count * (n + n - 2-i);
                    res.append(s.charAt(currentIndex));
            }
        }
        return res.toString();
        }

    public int getJ(int n ,int len){
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
