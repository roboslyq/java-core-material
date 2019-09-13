package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0066_plusone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlusOne {
    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        int[]  res = plusOne.plusOne(new int[]{9});
        Arrays.stream(res).forEach(System.out::println);

    }
    public int[] plusOne(int[] digits) {
        int carry = 0;
        for(int i = 1;i<=digits.length;i++){
            int current = 0;
            if(i == 1 ){
                current = digits[digits.length - i] + 1 + carry;
            }else{
                current = digits[digits.length - i] + carry;
            }
            carry = current >= 10 ? 1 :0;
            digits[digits.length - i] = current % 10;
            if(carry != 1){
                break;
            }
            if(i == digits.length && carry ==1){
                digits = new int[digits.length + 1];
                digits[0] = 1;
                break;
            }
        }

        return digits;
    }
}
