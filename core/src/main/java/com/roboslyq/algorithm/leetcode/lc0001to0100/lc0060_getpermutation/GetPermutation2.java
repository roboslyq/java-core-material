package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0060_getpermutation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯未剪枝，性能较差
 */
public class GetPermutation2 {
    public static void main(String[] args) {

    }
    public List<Integer>  res = new ArrayList<>();

    public String getPermutation(int n, int k) {
        List<Integer> linkedList = new LinkedList();
        for(int i = 1;i<=n;i++){
            linkedList.add(i);
        }
        getPermutation(0,linkedList);
        return res.get(k-1) +"";
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
