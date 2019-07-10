package com.roboslyq;

import java.util.*;

public class TestMain {
    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        System.out.println(testMain.lengthOfLongestSubstring("adaabcdefghaijkdabc"));
    }

    public int lengthOfLongestSubstring(String s) {
        List<String> list = Arrays.asList(s.split(""));
        List<String> listTmp = new LinkedList<>();
        int len = list.size();
        int resultCurrent = 0;
        int result = 0;
        int startIndex = 0;
        int endIndex = 0;
        listTmp.add(list.get(0));
        for(int i = 0; i < len;){
            startIndex = i;
            endIndex = i + 1;
            result = resultCurrent;

            for(int j = startIndex + 1; j < len;j++){
                String val = list.get(j);
                int valIndex = listTmp.indexOf(val);
                if(valIndex >= 0){
                    resultCurrent = listTmp.size();
                    if(result < resultCurrent){
                        result = resultCurrent;
                        if(result >= len/2){
                            return result;
                        }
                    }
                    listTmp.add(val);
                    i = j;
                    listTmp = listTmp.subList(valIndex + 1,listTmp.size());
                    break;
                }else{
                    listTmp.add(val);
                    endIndex++;
                    resultCurrent++;
                }
            }

        }
        return result;
    }


}

