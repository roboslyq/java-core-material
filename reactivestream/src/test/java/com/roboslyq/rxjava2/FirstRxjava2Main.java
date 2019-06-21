package com.roboslyq.rxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class FirstRxjava2Main {
    public static void main(String[] args) {
        RxJavaPlugins.setErrorHandler(e -> {
            System.out.println("exception 异常发生了");
        });
        GenerateObserverWithoutChainDemo.observerWithoutChain();
        GeneratedObserverWithChain.observerWithChain();
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
