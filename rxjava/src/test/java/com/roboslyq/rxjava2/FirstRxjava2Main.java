package com.roboslyq.rxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

import java.io.IOException;


public class FirstRxjava2Main {
    public static void main(String[] args) {
        RxJavaPlugins.setErrorHandler(e -> {
            System.out.println("exception 异常发生了");
        });
        observerWithoutChain();
        observerWithChain();
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void observerWithoutChain(){
        //1、创建被观察者
        Observable observable = Observable.create( e -> {
            int i = 1;
            while (true){
                if(i > 10 ){
                    break;
                }
                if(i % 5 == 0){
                    e.onError(new Throwable("异常测试-"+i));
                }else{
                    //事件来源，可以扩展为其它来源，并且可以使用阻塞队列阻塞当前线程，触发onNext事件
                    System.out.println("开始发送第"+ i +"个事件");
                    e.onNext(i);
                }
                Thread.sleep(1000);
                i++;
            }
            e.onComplete();
        });

        /**
         *2、创建观察者
         */
        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println( "no chain======================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println( "no chain======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println( "no chain======================onError");
               e.printStackTrace();
            }
            @Override
            public void onComplete() {
                System.out.println( "no chain======================onComplete");
            }
        };
        observable.onErrorReturn(val ->{
            System.out.println("on error return");
            return  Integer.valueOf("0");
        });
        //3、观察者开始观察，触发subscribe事件
        observable.subscribe(observer);
    }

    /**
     * 链式调用，创建观察者和被观察者
     */
    private static void observerWithChain(){
        //1、使用链式方式创建观察者
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            int i = 1;
            while (true){
                if(i > 10 ){
                    break;
                }
                if(i % 5 == 0){
                    e.onError(new Throwable("异常测试-"+i));
                }else{
                    //事件来源，可以扩展为其它来源，并且可以使用阻塞队列阻塞当前线程，触发onNext事件
                    System.out.println("开始发送第"+ i +"个chain事件");
                    e.onNext(i);
                }
                Thread.sleep(1000);
                i++;
            }
            e.onComplete();
        })  .subscribe(new Observer < Integer > () {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("chain ======================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("chain ======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("chain ======================onError");
            }

            @Override
            public void onComplete() {
                System.out.println("======================onComplete");
            }
        });
    }
}
