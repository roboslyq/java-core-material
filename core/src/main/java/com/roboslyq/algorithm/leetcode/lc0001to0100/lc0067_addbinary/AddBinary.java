package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0067_addbinary;

public class AddBinary {
    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.addBinary( "1010", "1011"));

    }
    public String addBinary(String a, String b) {
        int maxLength = a.length() > b.length()?a.length():b.length();
        int carry = 0;//进位标识
        String sb = "";
        for(int i = 1;i<=maxLength;i++){
            int v1 = 0;
            if(a.length() - i >= 0){
                v1 = a.charAt(a.length() - i) == '1' ? 1 : 0;
            }
            int v2 = 0;
            if(b.length() - i >= 0){
                v2 = b.charAt(b.length() - i) == '1' ? 1 : 0;
            }
            int current = v1 + v2 + carry;
            carry = current > 1 ? 1 :0;
            sb = current%2 == 0 ? "0"+sb:"1" + sb;
            if(i == maxLength  && carry == 1){
                sb = "1" + sb;
            }
        }

        return sb;
    }
}
