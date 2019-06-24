package com.roboslyq.rxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;

public class ObservableWithCount {
    /**
     * count使用方法测试
     */
    public static void observerWithChain(){
        //钩子函数扩展使用
        RxJavaPlugins.setOnObservableAssembly(originalObservable ->{
            print("before Observable");
            return  originalObservable;
        });
         Observable.create((ObservableOnSubscribe<Integer>) e -> {
            int i = 1;
            for(;;){
                i++;
                if(i > 10){
                    break;
                }
                e.onNext(i);
            }
            e.onComplete();
        }).filter( val ->{
            print(" 开始过滤值: " + val);
            return  val % 3 == 0;
        }).map(val ->{
            print("开始转换:" + val);
            return  val + 1;
        }).count()
                 .subscribe(new SingleObserver<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                print("chain 1======================onSubscribe");
            }

            @Override
            public void onSuccess(Long aLong) {
                print("chain 1======================onSuccess : " + aLong);

            }

            @Override
            public void onError(Throwable e) {
                print("chain 1======================onError");
            }

        });

    }
   private static void print(Object obj){
        System.out.println("[ current Thread : "+ Thread.currentThread().getName() + " ]" + obj);
    }
}
