package com.roboslyq.jdk.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        List<String> list = new ArrayList();
        for(int i = 0; i < 10; i++){
            list.add("Hello,word " + i);
        }
      /*  for(String val : list){
            System.out.println(val);
        }
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/

        list.parallelStream()
                .map(val -> val + "1")
//                .forEach(System.out :: println);
                .forEach(val ->{
                    System.out.println(Thread.currentThread().getId());
                });

    }
}
