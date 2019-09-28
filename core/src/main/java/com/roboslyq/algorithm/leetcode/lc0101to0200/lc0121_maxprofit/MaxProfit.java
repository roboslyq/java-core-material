package com.roboslyq.algorithm.leetcode.lc0101to0200.lc0121_maxprofit;

public class MaxProfit {
    /**
     * 最简单直观的双重循环处理法
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length<=1) return 0;
        int res = 0;
        for(int i=0;i<prices.length-1;i++){
            for(int j=i+1;j<prices.length;j++){
                if(prices[j] - prices[i] > res){
                    res = prices[j] - prices[i];
                }
            }
        }
        return res;
    }

    /**
     * 股票规律：谷底峰值(卖出与买入最大差值)
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        if(prices == null || prices.length<=1) return 0;
        int profit = 0;//profit默认值为0，表不没有买入。即数据是降序排列，没有卖出的机会
        int minPrice = Integer.MAX_VALUE;
        for(int i=0;i<prices.length;i++){
           if(prices[i] < minPrice ){
               minPrice = prices[i];//交换最小点
           }else {
               if(prices[i] - minPrice > profit){
                   profit = prices[i] - minPrice;
               }
           }
        }
        return profit;
    }
    public int maxProfit2(int[] prices) {
        int sum = 0;
        int result = 0;
        for(int i =1;i<prices.length;i++){
            sum = sum+prices[i]-prices[i-1];
            if(sum<0){
                sum = 0;
            }
            if(sum>result){
                result = sum;
            }
        }
        return result;
    }
}
