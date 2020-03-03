/*
 * Copyright (C), 2015-2019
 * FileName: JavaFlowApiMain1
 * Author:   luo.yongqian
 * Date:     2019/5/6 15:36
 * Description: java stream api测试
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/6 15:36      1.0.0               创建
 */
package com.roboslyq.java9flowapi;

import com.roboslyq.java9flowapi.dto.SimpleDto;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import com.roboslyq.Log;

/**
 * 〈java stream api测试〉
 *
 * @author luo.yongqian
 * @create 2019/5/6
 * @since 1.0.0
 */
public class JavaFlowApiMain2 {
    public static void main(String[] args) throws InterruptedException {
        Log.print("开始主线程--");
        //1、创建生产者
        SubmissionPublisher<SimpleDto> publisher = new SubmissionPublisher<>();
        //2、创建订阅者
        Flow.Subscriber<SimpleDto> subscriber = new DemoSubscribe();
        Flow.Subscriber<SimpleDto> subscriber2 = new DemoSubscribe();
        //3、订阅者发起订阅
        publisher.subscribe(subscriber);
        publisher.subscribe(subscriber2);

        //初始化事件源,此值如果大于256(即BufferdSubscription中的缓存值，Publisher则会被阻塞。)
        DtoHelper.getSimpleDtos(2000)
                .forEach(                   //遍列事件源
                        simpleDto -> {
                            Log.print("生产元素：" + simpleDto.getName());
                            publisher.submit(simpleDto);   //每一次遍列发一条消息给订阅者
                        }
                );
        //因上面的流程是异步的，所以如果此处不sleep等待一下，控制台可能不会打印信息
        Thread.sleep(1000);
        //关闭流
        publisher.close();
    }
}

class DemoSubscribe implements Flow.Subscriber<SimpleDto>{

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        Log.print("开始订阅");
        //订阅事件，订阅成功后，初次请求消费1个元素
        this.subscription.request(1);
    }

    @Override
    public void onNext(SimpleDto item) {
        Log.print("消费元素-->" + item.getId());
        //模拟业务消费
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //当前元素已完成消费，请求新的元素消费
        subscription.request(1);
        if (item.getId() == 9) {
            //可以此处手动完成任务
//                    this.onComplete();
            //如果我们取消订阅，则订阅者将不会收到onComplete或onError信号。
//                    subscription.cancel();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        Log.print("onComplete完成-->end");
    }
}