/**
 * Copyright (C), 2015-2020
 * FileName: RocketMQConsumer
 * Author:   luo.yongqian
 * Date:     2020/12/30 16:32
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/12/30 16:32      1.0.0               创建
 */
package com.roboslyq.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/12/30
 * @since 1.0.0
 */
@Service
@RocketMQMessageListener(topic = "testTopic",  consumerGroup = "test-consumer")
public class RocketMQConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    // 实现消息的消费处理
    @Override
    public void onMessage(MessageExt message) {
        System.out.printf("--->MessageExtConsumer received message, msgId:%s, body:%s %n ", message.getMsgId(), new String(message.getBody()));
    }

    // 设置从当前时间点开始消费消息
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
    }
}