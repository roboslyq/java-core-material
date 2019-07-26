package com.roboslyq.algorithm.leetcode.lc0005_longestpalindrome;

/**
 * 马拉车算法实现回文串
 */
public class Manacher {
    public static void main(String[] args) {
        Manacher palindrome = new Manacher();
//        String test1 ="abcbaaabc";
//        String test2 ="babad";
//        String test2 ="cbbd";
//        String test2 = "ccd";
//        String test2 = "ccc";
//        String test2 = "abcda";
        String pre =   "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa";
        String test2 = "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa";
        String esult = "aaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhhiiiiiiiiiijjjjjjjjjjkkkkkkkkkkllllllllllmmmmmmmmmmnnnnnnnnnnooooooooooppppppppppqqqqqqqqqqrrrrrrrrrrssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxxxxxxxxxxyyyyyyyyyyzzzzzzzzzzyyyyyyyyyyxxxxxxxxxxwwwwwwwwwwvvvvvvvvvvuuuuuuuuuuttttttttttssssssssssrrrrrrrrrrqqqqqqqqqqppppppppppoooooooooonnnnnnnnnnmmmmmmmmmmllllllllllkkkkkkkkkkjjjjjjjjjjiiiiiiiiiihhhhhhhhhhggggggggggffffffffffeeeeeeeeeeddddddddddccccccccccbbbbbbbbbbaaaa";
        //        String test2 = "bbbb";
        String test3 ="abcda";
//        System.out.println("abcd".substring(1,2));
        System.out.println(palindrome.longestPalindrome(test2));
        System.out.println(palindrome.longestPalindrome(test3));
//        System.out.println(palindrome.longestPalindrome(test3));
    }
    public String longestPalindrome(String s) {
        //处理特殊长度字符串
        if(s == null || s.length() <= 1){
            return s;
        }
        // exp: 原串："abcd" , 处理后串："$#a#b#c#d#*"
        //首尾为$ 和 * ，非回文串，不影响结果。中间添加 s.length + 1个#,即共2 + 2*len + 1长度字符，一定为奇数
        StringBuilder sb = new StringBuilder("$#");
        for (int i = 0;i<s.length();i++){
            sb.append(s.charAt(i)).append("#");
            if(i == s.length() - 1){
                sb.append("*");
            }
        }
        //定义相应长度的数组
        Integer[] p = new Integer[sb.length()];
        //初始化数据
        for (int i=0;i<sb.length();i++){
            p[i] = 0;
        }
        /**
         *  mx: 所有子回文串中，出现在右边的最大值
         *  center：即mx所在中心点(exp: aba，当mx = 2时，中心点为b)
         */
        Integer  center = 0 ;
        Integer  mx = 0;
        String maxStr = "";
        //循环所有的添加字符后的串
        for(Integer i = 0; i < p.length ;i++){
            int j;
            //此分支为Manacher算法关键，节约时间，因为p[i]直接赋值为Math.min(mx-i, p[j])
            if (i < mx){
                j = 2 * center - i ;//i 关于 center 的对称点
                p[i] = Math.min(mx-i, p[j]);
            }
            //在p[i] = Math.min(mx-i, p[j])基础之上，尝试继续向两边扩展，更新 p[i]
            while ( i - p[i] - 1 >= 0               //保证左边大于0
                    && i + p[i] + 1 < sb.length() -1    //保证右边不能过最大长度
                    && sb.charAt(i - p[i] - 1) == sb.charAt(i + p[i] + 1)//还是回文串，继续递归
                ){
                p[i] = p[i]  + 1;
            }
            //更新中心
            if (i + p[i] > mx){
                mx = i + p[i];
                center = i;
            }
            // 更新最长串
            if (1 + 2 * p[i] > maxStr.length()){
                maxStr = sb.substring(i - p[i], i + p[i] + 1);
            }
        }
        return maxStr.replace("#", "");
    }
}
