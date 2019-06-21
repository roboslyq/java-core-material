package com.roboslyq.rxjava2;

import io.reactivex.plugins.RxJavaPlugins;

public class FirstRxjava2Main {
    public static void main(String[] args) {
        RxJavaPlugins.setErrorHandler(e -> {
            System.out.println("exception 异常发生了");
        });
        //GenerateObserverWithoutChainDemo.observerWithoutChain();
        GeneratedObserverWithChain.observerWithChain();
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
