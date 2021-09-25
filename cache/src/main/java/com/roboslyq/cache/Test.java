/**
 * Copyright (C), 2015-2020
 * FileName: Test
 * Author:   luo.yongqian
 * Date:     2020/4/24 17:45
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/4/24 17:45      1.0.0               创建
 */
package com.roboslyq.cache;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 〈〉
 *
 * @author roboslyq
 * @date 2020/4/24
 * @since 1.0.0
 */
public class Test {
    public static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        init(10);
        System.out.println(add(1, 0, ""));
        System.out.println(add(2, 0, ""));
        System.out.println(add(3, 0, ""));
        System.out.println(add(4, 0, ""));
        System.out.println(add(5, 0, ""));
        System.out.println(add(6, 0, ""));
        System.out.println(add(7, 0, ""));
        System.out.println(add(8, 0, ""));
        System.out.println(add(9, 0, ""));
        System.out.println(add(10, 0, ""));


    }

    public static void init(int k) {
        int num = 1;
        for (int i = 0; i < k; i++) {
            num = num * 3;
            arrayList.add(num);
        }
    }

    public static String add(int currCount, int preIndex, String preStr) {
        int sum = 0;
        int i = 0;
        for (; i < arrayList.size(); i++) {
            sum += arrayList.get(i);
            if (sum >= currCount) {
                if(preIndex>0){
                    for (int j = 1; j < preIndex - i; j++) {
                        preStr += "2";
                    }
                }
                    int currFirst =  i>=1 ?currCount / arrayList.get(i - 1) : currCount;
                    if (currFirst == 1) {
                        preStr += "2";
                    }
                    if (currFirst == 2) {
                        preStr += "3";
                    }
                    if (currFirst == 3) {
                        preStr += "5";
                    }
                    if(currCount > 0){
                        currCount = i>=1 ?currCount % arrayList.get(i - 1) : 0;
                    }else {
                        currCount = -1;
                    }

                break;
            }

        }
        if (currCount > 0) {
            preStr =  add(currCount, i, preStr);
        }
        int N = 0;
        try {
            Thread.sleep(N*24*60*60*1000);
        } catch (InterruptedException e) {
            System.out.println("又被打段了，得重新获取N天后日期...");
        }
        return preStr;
    }


}