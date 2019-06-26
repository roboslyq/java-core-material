/**
 * Copyright (C), 2015-2019
 * FileName: ChainsMain
 * Author:   luo.yongqian
 * Date:     2019/6/25 13:28
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/6/25 13:28      1.0.0               创建
 */
package com.roboslyq.chains;

/**
 *
 * 〈〉
 * @author luo.yongqian
 * @create 2019/6/25
 * @since 1.0.0
 */
public class ChainsMain {
    public static void main(String[] args) {
        Publishers.create((OnSubscribeProcessor<Integer>)subscriber -> {
            int i = 0;
            while ( i< 10 ){
                System.out.println("第 " + (++i) +"次生产");
                subscriber.onNext(i);
            }
        }).filter(val ->{
            System.out.println("filter--" + val);
            return val != null;
        }).map(val ->{
            System.out.println("map--" + val);
            return  val + "";
        }).subscribe(( Subscriber<String>)new Subscriber() {
            @Override
            public void onSubscribe(OnSubscribeProcessor var1) {
                System.out.println("subscribe "+ var1);
            }

            @Override
            public void onNext(Object var1) {
                System.out.println("subscribe "+ var1);
            }

            @Override
            public void onError(Throwable var1) {
                System.out.println("Throwable "+ var1);

            }
            @Override
            public void onComplete() {
                System.out.println("onComplete ");
            }
        });
    }
}