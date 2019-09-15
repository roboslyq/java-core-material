package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0060_getpermutation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**

 */
public class GetPermutation {
    public static void main(String[] args) {
        GetPermutation getPermutation = new GetPermutation();
//        List<Integer> linkedList = new LinkedList();
//        for(int i = 1;i<= 4;i++){
//            linkedList.add(i);
//        }
//        getPermutation.getPermutation(0,linkedList);
//        getPermutation.res.stream().forEach(System.out::println);
        System.out.println( "res----------- "+ getPermutation.getPermutation(4,9));
    }
    public List<Integer>  res = new ArrayList<>();
    public String getPermutation(int n, int k) {
        List<Integer> linkedList = new LinkedList();
        for(int i = 1;i<=n;i++){
            linkedList.add(i);
        }
        getPermutation(0,linkedList,k);
        System.out.println(res);
        return res+"";
    }

    public void getPermutation(int prefix ,List list,int k){
        count++;
        if(list.size() == 0 && count == k){
            this.res = prefix;
            return;
        }
        for(int i = 0;i<list.size();i++){
            if(count <= k ){
                List<Integer> tmp = new LinkedList<>(list);
                getPermutation(prefix*10 + tmp.remove(i) ,tmp,k);
            }else{
                break;
            }
        }
    }
}
