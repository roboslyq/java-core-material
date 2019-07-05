package com.roboslyq.concurrent.forkjoin;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

public class CountedCompleterDemo{
    public static void main(String[] args) {
        CountedCompleterDemo ccd = new CountedCompleterDemo();
        ccd.testDivideSearch();
    }
    public void testDivideSearch(){
        Integer[] array = new Integer[10000000];
        for(int i = 0; i < array.length; i++){
            array[i] = i+1;
        }
        AtomicReference<Integer> result = new AtomicReference<>();
        Integer find = new SearchCounterCompleterDemo<>(null, array, result, 0,
                array.length - 1,this::match).invoke();
        System.out.printf("查找结束,任务返回:{},result:{}",find,result.get());

    }



    private boolean match(Integer x) {
        return x > 2000000 &&  x%2 ==0 && x%3 == 0 && x%5 ==0 && x %7 ==0;
    }
}
