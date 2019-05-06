/*
 * Copyright (C), 2015-2019
 * FileName: JavaStreamApiMain1
 * Author:   luo.yongqian
 * Date:     2019/5/6 15:36
 * Description: java stream api测试
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/6 15:36      1.0.0               创建
 */
package com.roboslyq.java9stream;

import roboslyq.java9stream.SimpleDto;
import roboslyq.java9stream.SimpleDtoHelper;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * 〈java stream api测试〉
 *
 * @author luo.yongqian
 * @create 2019/5/6
 * @since 1.0.0
 */
public class JavaStreamApiMain1 {
    public static void main(String[] args) throws InterruptedException {
        print();
        SubmissionPublisher<SimpleDto> publisher = new SubmissionPublisher<>();
        //通过匿名类创建Flow.Subscriber对象。
        publisher.subscribe(new Flow.Subscriber<>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("subscription");
                print();
                //订阅事件，订阅成功后，初次请求消费1个元素
                this.subscription.request(1);
            }

            @Override
            public void onNext(SimpleDto item) {
                System.out.println("消费元素-->" + item.getId());
                print();
                //当前元素已完成消费，请求新的元素消费
                subscription.request(1);
                if (item.getId() == 9) {
                    //可以此处手动完成任务
                    this.onComplete();
                    //如果我们取消订阅，则订阅者将不会收到onComplete或onError信号。
                    //subscription.cancel();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });

        SimpleDtoHelper
                .getSimpleDtos(10)          //初始化事件源
                .forEach(                   //遍列事件源
                        publisher::submit   //每一次遍列发一条消息给订阅者
                );
        //因上面的流程是异步的，所以如果此处不sleep等待一下，控制台可能不会打印信息
        Thread.sleep(1000);
        //关闭流
        publisher.close();
    }

    /**
     * 打印当前线程信息，以检验是否异步
     */
    private static void print() {
        System.out.printf("当前线程：%s,%s \n", Thread.currentThread().getId(), Thread.currentThread().getName());
    }

}