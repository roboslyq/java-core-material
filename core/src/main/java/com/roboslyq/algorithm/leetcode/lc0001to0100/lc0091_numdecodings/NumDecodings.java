package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0091_numdecodings;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumDecodings {
    public static void main(String[] args) {
        NumDecodings numDecodings = new NumDecodings();
        System.out.println(numDecodings.numDecodings("12"));
    }

    /**
     * 斐波那契数列
     * 1、公式：F(n) = F(n-1) + F(n-2)
     * 2、例如字符串：xxxxxxabc 解法为 f(a) + f(b)。若bc是合理解
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        //特殊情况处理
        if(s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        //n-1的数值（相当于f(1)初始值）
        int val_i_1 = 1;
        //n-2的值（相当于f(0)初始值）
        int val_i_2 = 1;
        //当前值(如果元素length = 1 且不为0时，返回此默认值1）
        int val_cur = 1;
        for(int i=2;i<=s.length();i++){
            val_cur = 0;
            //如果i-1不为0，表示i-1有解
            if(s.charAt(i-1) != '0'){
                val_cur = val_i_1;
            }
            //i-2表示2位解，则i-2必须为1或者为2但同时保证i-1 <=6 有效
            if(s.charAt(i-2)== '1' ||  (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6')){
                val_cur += val_i_2;
            }
            //将i-1设置为i-2
            val_i_2 = val_i_1;
            //将val_curr设置为i-1.即指针后移
            val_i_1 = val_cur;
        }
        return val_cur;
    }

    /**
     * 使用数组保存临时值
     * @param s
     * @return
     */
    public int numDecodings1(String s) {
        //特殊情况处理
        if(s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        //数组
        int[] dp = new int[s.length()+ 1];
        dp[0] = dp[1] = 1;
        for(int i=2;i<=s.length();i++){
            //如果i-1不为0，表示i-1有解
            if(s.charAt(i-1) != '0'){
                dp[i] = dp[i-1];
            }
            //i-2表示2位解，则i-2必须为1或者为2但同时保证i-1 <=6 有效
            if(s.charAt(i-2)== '1' ||  (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6')){
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
    }

    /**
     * 动态规划法：
     * public int numDecodings(String s) {
     *         int n = s.length();
     *         if (n == 0) return 0;
     *
     *         int[] dp = new int[n+1];
     *         dp[n]  = 1;
     *         dp[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
     *
     *         for (int i = n - 2; i >= 0; i--)
     *             if (s.charAt(i) == '0') continue;
     *             else dp[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? dp[i+1]+dp[i+2] : dp[i+1];
     *
     *         return dp[0];
     *     }
     *
     * 作者：user9827R
     * 链接：https://leetcode-cn.com/problems/decode-ways/solution/jie-ma-fang-fa-by-user9827r/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int numDecodings2(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] dp = new int[n+1];
        dp[n]  = 1;
        dp[n-1] = s.charAt(n-1) != '0' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0') continue;
            else dp[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? dp[i+1]+dp[i+2] : dp[i+1];

        return dp[0];
    }


}
