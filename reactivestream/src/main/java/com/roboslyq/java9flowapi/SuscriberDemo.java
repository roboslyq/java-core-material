/**
 * Copyright (C), 2015-2019
 * FileName: SuscriberDemo
 * Author:   luo.yongqian
 * Date:     2019/5/6 17:42
 * Description: SimlpeDto1的订阅者
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/6 17:42      1.0.0               创建
 */
package com.roboslyq.java9flowapi;

import com.roboslyq.java9flowapi.dto.SimpleDto1;

import java.util.concurrent.Flow;

/**
 *
 * 〈SimlpeDto1的订阅者，实现Flow.Subscriber接口〉
 *  此实现方式为普通的Java接口实现操作，不是很简洁。可以直接使用
 * @author luo.yongqian
 * @create 2019/5/6
 * @since 1.0.0
 */
public class SuscriberDemo implements Flow.Subscriber<SimpleDto1> {

    /**
     * 1、定义一个类属性，接收onSubscribe()事件传递的Subscription对象。用来与Publisher通讯。
     * 2、Subscription发布者和订阅者之间创建异步非阻塞链接。
     * 3、(1)订阅者调用其request方法来向发布者请求项目。
     *    (2)订阅者调用其cancel取消订阅的方法，即关闭发布者和订阅者之间的链接。
     *
     */
    private Flow.Subscription subscription;

    /**
     * 1、订阅事件，这是订阅者订阅了发布者后接收消息时调用的第一个方法。
     * 2、通常我们调用subscription.request开始从处理器（Processor）接收项目
     * @param subscription
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("start subscription...");
        this.subscription = subscription;
        subscription.request(1);
    }

    /**
     * 当从发布者收到消息时调用此方法并且需要重新发送request,告诉publisher可以继承推送消息。
     * @param item
     */
    @Override
    public void onNext(SimpleDto1 item) {
        System.out.println("simpleDto1 event--"+ item.getId() + "-"+ item.getName());
        subscription.request(1);
    }

    /**
     * 发生不可恢复的错误时调用此方法，我们可以在此方法中执行清理操作，例如关闭数据库连接。
     * @param throwable
     */
    @Override
    public void onError(Throwable throwable) {
        System.out.println("error");
    }

    /**
     * 这就像finally方法，并且在发布者没有发布其他项目或发布者关闭时调用。
     * 我们可以用它来发送流成功处理的通知
     */
    @Override
    public void onComplete() {
        System.out.println("done");
    }
}