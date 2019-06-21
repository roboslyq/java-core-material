package com.roboslyq.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 使用链式结构创建Observer
 */
public class GenerateObserverWithoutChainDemo {
    public static void observerWithoutChain(){
        //1、创建被观察者
        Observable observable = Observable.create(e -> {
            int i = 1;
            while (true){
                if(i > 10 ){
                    break;
                }
                if(i % 5 == 0){
                    printThread();
                    e.onError(new Throwable("异常测试-"+i));
                }else{
                    //事件来源，可以扩展为其它来源，并且可以使用阻塞队列阻塞当前线程，触发onNext事件
                    System.out.println("开始发送第"+ i +"个事件");
                    printThread();
                    e.onNext(i);
                }
                Thread.sleep(1000);
                i++;
            }
            e.onComplete();
        }).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread());

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
                printThread();
                System.out.println( "no chain======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                printThread();
                System.out.println( "no chain======================onError");
                e.printStackTrace();
            }
            @Override
            public void onComplete() {
                printThread();
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

    public static void printThread(){
        System.out.println(Thread.currentThread().getId()+"--"+Thread.currentThread().getName());
    }
}
