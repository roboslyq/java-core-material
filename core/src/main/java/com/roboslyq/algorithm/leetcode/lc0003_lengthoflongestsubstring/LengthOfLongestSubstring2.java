package com.roboslyq.algorithm.leetcode.lc0003_lengthoflongestsubstring;

public class LengthOfLongestSubstring2 {
    public static void main(String[] args) {
        LengthOfLongestSubstring2 testMain = new LengthOfLongestSubstring2();
//        System.out.println(testMain.lengthOfLongestSubstring("adaabcdefghaijkdabc"));
//       System.out.println(testMain.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(testMain.lengthOfLongestSubstring(" "));
//        System.out.println(testMain.lengthOfLongestSubstring("asljlj"));
//        System.out.println(testMain.lengthOfLongestSubstring("abcdefg"));
        System.out.println(testMain.lengthOfLongestSubstring("jyldtgrjrhosfglrcnspvy"));
//        System.out.println(testMain.lengthOfLongestSubstring("bpfbhmipx"));
        //System.out.println(testMain.lengthOfLongestSubstring("jyldtgrjrhosfglrcnspvy"));
    }

     private int lengthOfLongestSubstring(String s) {
        int result = 0;
        int resultStartIndex = 0; //当前结果串所在位置
        int len = s.length();
        for(int sourceIndex = 0;sourceIndex< len;sourceIndex++){
           for(int i = resultStartIndex;i<sourceIndex;i++){
                if(s.charAt(i) == s.charAt(sourceIndex)){
                    result = Math.max(sourceIndex - resultStartIndex,result);
                    resultStartIndex = i + 1;
                }
           }
           if(sourceIndex == len - 1){
               result = Math.max(sourceIndex - resultStartIndex + 1,result);
           }
       }
        return result;
    }


}

