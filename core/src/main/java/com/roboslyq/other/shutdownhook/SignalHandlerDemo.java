/**
 * Copyright (C), 2015-2020
 * FileName: SignalHandler
 * Author:   luo.yongqian
 * Date:     2020/3/4 23:41
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/3/4 23:41      1.0.0               创建
 */
package com.roboslyq.other.shutdownhook;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.concurrent.TimeUnit;

/**
 * 〈〉
 *
 * @author luo.yongqian
 * @date 2020/3/4
 * @since 1.0.0 */
public class SignalHandlerDemo {

    public static void main(String[] args){
        SignalHandlerImpl signalHandler = new SignalHandlerImpl();

        // 注册对指定信号的处理
        Signal.handle(new Signal("SHUTDOWN"), signalHandler);
        System.out.println("running... ");
        for (;;){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SignalHandlerImpl implements SignalHandler {
    @Override
    public void handle(Signal signal) {
        // 信号量名称
        String signalName = signal.getName();
        // 信号量数值
        int signalNumber = signal.getNumber();
        if (signalName.equals("SHUTDOWN") && signalNumber == 15) {
            System.out.println("cleaning...");
            System.out.println("shutdown!!!");
            System.exit(0);
        }
    }
}
}