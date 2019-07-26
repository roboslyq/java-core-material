package com.roboslyq.algorithm.leetcode.lc0008_myatoi;

/**
 * 此题关键是判断整数溢出。
 * 如果不考虑整数溢出直接使用Long转int,然后判断是否相等。如果相等，则表示没有溢出，否则有溢出
 */
public class MyAtoi {
    public static void main(String[] args) {
        MyAtoi myAtoi = new MyAtoi();
//        System.out.println(myAtoi.myAtoi("010"));
//        System.out.println(myAtoi.myAtoi("-01324000"));
//        System.out.println(myAtoi.myAtoi("-2147483647"));
//        System.out.println(myAtoi.myAtoi("-2147483649"));
//        System.out.println(myAtoi.myAtoi("2147483800"));
//        System.out.println(myAtoi.myAtoi("2147483646"));
        System.out.println(myAtoi.myAtoi("2147483648"));
    }
    public int myAtoi(String str) {
        final String  NUMBER = "0123456789";
        if(str == null || str.trim().length() == 0){
            return 0;
        }else{
            str = str.trim();
        }
        int flag = 1;
        String strTmp;
        if(str.startsWith("-")){
            flag = -1;
            strTmp = str.substring(1);
        }else if(str.startsWith("+")){
            strTmp = str.substring(1);
        }else{
            strTmp = str;
        }
        int resInt = 0;
        for(int i = 0; i< strTmp.length();i++){
            char strI = strTmp.charAt(i);
            if(resInt == 0 && NUMBER.indexOf(strI) == 0 ){
                continue;
            }
            if(NUMBER.indexOf(strI) >= 0){
                int curAtI = Integer.parseInt(strI+"");
                if(flag == 1 && (resInt  > Integer.MAX_VALUE/10 || (resInt  == Integer.MAX_VALUE/10) && curAtI >=7)){
                    resInt = Integer.MAX_VALUE;
                    break;
                }
                if(flag == -1 && (resInt*flag < Integer.MIN_VALUE/10 || (resInt*flag == Integer.MIN_VALUE/10 && curAtI>=8))){
                    resInt = Integer.MIN_VALUE;
                    break;
                }
                resInt = resInt*10 +  curAtI;
            }else{
                break;
            }
        }
        return resInt * flag ;
    }
}
