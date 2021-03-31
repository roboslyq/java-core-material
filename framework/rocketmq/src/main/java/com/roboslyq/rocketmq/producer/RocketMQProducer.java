/**
 * Copyright (C), 2015-2020
 * FileName: RocketMQProducer
 * Author:   luo.yongqian
 * Date:     2020/12/30 14:43
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/12/30 14:43      1.0.0               创建
 */
package com.roboslyq.rocketmq.producer;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/12/30
 * @since 1.0.0
 */
@Service
public class RocketMQProducer implements ApplicationRunner {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    public void sendmessage(){
        int i =0 ;
        // Send string
        while (true) {
            try {
                SendResult sendResult = rocketMQTemplate.syncSend("testTopic", "Hello, World!",1000);
                System.out.printf("syncSend %d to topic %s sendResult=%s %n", i++,"testTopic", sendResult);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }catch (Exception e){

            }

        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendmessage();
    }
}