package com.zhoushiya.springcloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author zhoushiya
 * @date 2020/10/1 13:49
 */
@Service
// 绑定为消息消费端
@EnableBinding(Sink.class)
public class MessageConsumerImpl implements MessageConsumer {

    @StreamListener(Sink.INPUT)
    @Override
    public void receiveMessage(String message) {
        System.out.println(message);
    }
}
