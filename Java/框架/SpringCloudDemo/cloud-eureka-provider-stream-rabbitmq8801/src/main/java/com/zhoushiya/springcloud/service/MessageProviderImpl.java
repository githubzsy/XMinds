package com.zhoushiya.springcloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @author zhoushiya
 * @date 2020/10/1 13:00
 */
// 绑定角色为消息提供者
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {

    /**
     * 消息发送管道
     */
    private final MessageChannel output;

    public MessageProviderImpl(MessageChannel output) {
        this.output = output;
    }

    /**
     * 发送流水号
     * @return
     */
    @Override
    public String sendMessage() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        return serial;
    }
}
