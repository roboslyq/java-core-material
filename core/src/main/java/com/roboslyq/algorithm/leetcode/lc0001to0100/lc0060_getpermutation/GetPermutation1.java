package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0060_getpermutation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 此处袜枚举，未实现当前题目要求
 */
public class GetPermutation1 {
    public static List<Integer>  res = new ArrayList<>();
    public static void main(String[] args) {
        GetPermutation1 getPermutation = new GetPermutation1();
        List<Integer> linkedList = new LinkedList();
        for(int i = 1;i<= 4;i++){
            linkedList.add(i);
        }
        getPermutation.getPermutation(0,linkedList);
        res.stream().forEach(System.out::println);
    }
    public String getPermutation(int n, int k) {
        List<Integer> linkedList = new LinkedList();
        for(int i = 1;i<=n;i++){
            linkedList.add(i);
        }
        getPermutation(0,linkedList);
        return "";
    }

    public void getPermutation(int prefix ,List list){
        if(list.size() == 0){
            res.add(prefix) ;
            return;
        }
        for(int i = 0;i<list.size();i++){
            List<Integer> tmp = new LinkedList<>(list);
            getPermutation(prefix*10 + tmp.remove(i) ,tmp);
        }
    }
}
