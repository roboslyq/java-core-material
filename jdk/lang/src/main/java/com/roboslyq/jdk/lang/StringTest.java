package com.roboslyq.jdk.lang;

public class StringTest {
    public static void main(String[] args) {
        String str1 = new String("str1");
        char[] charArray = {'s','t','r','2'};
        String str2= new String(charArray);
        print(str1);
        print(str2);

    }

    static void print(String str){
        System.out.println("------>" + str);
    }
}
