package com.zhoushiya.springcloud.service;

/**
 * @author zhoushiya
 * @date 2020/10/1 13:47
 */
public interface MessageConsumer {
    /**
     * 接收消息
     * @return
     */
    void receiveMessage(String message);
}
