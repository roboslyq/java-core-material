package com.roboslyq;

import java.util.*;

public class TestMain {
    public static void main(String[] args) {
        TestMain testMain = new TestMain();
//        System.out.println(testMain.lengthOfLongestSubstring("adaabcdefghaijkdabc"));
//        System.out.println(testMain.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(testMain.lengthOfLongestSubstring(" "));
//        System.out.println(testMain.lengthOfLongestSubstring("asljlj"));
//        System.out.println(testMain.lengthOfLongestSubstring("bpfbhmipx"));
        System.out.println(testMain.lengthOfLongestSubstring("jyldtgrjrhosfglrcnspvy"));
    }

     private int lengthOfLongestSubstring(String s) {
         if(s == null )  return 0;
         if(s.length() <= 1 ) return s.length();
        List<Character> listTmp = new LinkedList<>();
        int len = s.length();
        int result = 0;
        int startIndex;
        listTmp.add(s.charAt(0));
        for(int i = 0; i <len;){
            startIndex = i;
            for(int j = startIndex + 1; j < len;j++){
                char val = s.charAt(j);
                int valIndex = listTmp.indexOf(val);
                if(valIndex >= 0){
                    int resultCurrent = listTmp.size();
                    if(result <= resultCurrent){
                        result = resultCurrent;
                    }
                    if( result > ((len -i) + (resultCurrent-valIndex)) //如果满足当前result 大于剩下的字节数，那么可以提前结果
                            || j == len -1 ){//所有字符串已经匹配完成
                        return result;
                    }
                    listTmp.add(val);
                    i = j;
                    listTmp = listTmp.subList(valIndex + 1,listTmp.size());
                    break;
                }else{
                    listTmp.add(val);
                }
            }
//            result =  listTmp.size();
        }
        return result;
    }


}

