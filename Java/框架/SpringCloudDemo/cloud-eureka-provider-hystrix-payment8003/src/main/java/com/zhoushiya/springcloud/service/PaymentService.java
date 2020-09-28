package com.zhoushiya.springcloud.service;

/**
 * @author zhoushiya
 * @date 2020/9/28 14:45
 */
public interface PaymentService {
    /**
     * 让线程等待多少毫秒，然后返回线程名称
     * @param millis 毫秒数
     * @return 线程名称
     * @throws InterruptedException
     */
    String waitTime(long millis) throws InterruptedException;
}
