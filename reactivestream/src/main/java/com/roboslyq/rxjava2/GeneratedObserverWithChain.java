package com.roboslyq.rxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GeneratedObserverWithChain {
    /**
     * 链式调用，创建观察者和被观察者
     */
    public static void observerWithChain(){
        //1、使用链式方式创建观察者
        Observable observable =  Observable.create((ObservableOnSubscribe<Integer>) e -> {
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
        });
        observable.subscribe(new Observer< Integer >() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("chain 1======================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("chain 1======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("chain 1======================onError");
            }

            @Override
            public void onComplete() {
                System.out.println("====================== onComplete 1");
            }
        });
        observable.subscribe(new Observer< Integer >() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("chain2 ======================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("chain2 ======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("chain2 ======================onError");
            }

            @Override
            public void onComplete() {
                System.out.println("======================onComplete 2");
            }
        });
    }
}
