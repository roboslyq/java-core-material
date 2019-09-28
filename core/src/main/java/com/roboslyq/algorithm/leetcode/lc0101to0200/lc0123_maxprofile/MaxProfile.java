package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0123_maxprofile;

public class MaxProfile {
    public static void main(String[] args) {
        MaxProfile maxProfile = new MaxProfile();
        System.out.println(maxProfile.maxProfit(new int[]{3,3,5,0,0,3,1,4}));
    }
    /**
     * 此解有误，下面情况无法处理：
     *
     * 求出收益第一高和第二高的两次买卖，然后加起来。对于普通的情况是可以解决的，但是对于下边的情况
     *
     * 1 5 2 8 3 10
     * 第一天买第二天卖，第三天买第四天卖，第五天买第六天卖，三次收益分别是 4，6，7，最高的两次就是 6 + 7 = 13 了，但是我们第二天其实可以不卖出，第四天再卖出，那么收益是 8 - 1 = 7，再加上第五天买入第六天卖出的收益就是 7 + 7 = 14了。
     *
     * 所以当达到了一个高点时不一定要卖出，所以需要考虑的情况就很多了，不能像 121 题 ， 122 题 那样简单的考虑了。那只能朝着动态规划思路想了。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length<=1) return 0;
        //profitMax_less ，profitMax_larger 设置默认值为0，表不没有买入。
        int profitMax_larger = 0;
        int profitMax_less = 0;
        //最低价格初始化
        int minPrice = Integer.MAX_VALUE;
        //当前可赚最高利益
        int curProfile = 0;
        int curProfileTmp = 0;
        for(int i=1;i<prices.length;i++){
            curProfileTmp = prices[i] - prices[i-1];
            if(curProfileTmp > 0 ){
                curProfile += curProfileTmp;
                if(i<prices.length-1){
                    continue;
                }
            }
            if(curProfile > profitMax_less){
                if(curProfile > profitMax_larger){
                    profitMax_less = profitMax_larger ;
                    profitMax_larger = curProfile;
                }else{
                    profitMax_less = prices[i] - minPrice;
                }
            }
            curProfile = 0;
            //重新初始化谷底
            minPrice = prices[i];
        }
        return profitMax_larger + profitMax_less;
    }
}
