package com.zhoushiya.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * @author zhoushiya
 * @date 2020/9/28 14:46
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    /**
     * 让线程等待多少毫秒，然后返回线程名称
     *
     * @param millis 毫秒数
     * @return 线程名称
     * @throws InterruptedException
     */
    @Override
    /**
     * 设置兜底方法为waitTime_ExHandler
     * 设置超时时间为3秒钟
     */
    @HystrixCommand(fallbackMethod = "waitTime_FallbackHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String waitTime(long millis) throws InterruptedException {
        Thread.currentThread().sleep(millis);
        return "线程：" + Thread.currentThread().getName() + " time over";
    }

    public String waitTime_FallbackHandler(long millis) {
        return "线程：" + Thread.currentThread().getName() + "：您访问的服务暂时不可用，请稍后再来";
    }

    /**
     * 服务熔断处理测试，输入负数则出错
     * @return
     * @throws InterruptedException
     */
    @Override
    @HystrixCommand(fallbackMethod = "circuitBreaker_Fallback", commandProperties = {
            // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 失败率达到60%后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String circuitBreaker(int i){
        if(i<0){
            throw new RuntimeException("不能为负数");
        }
        return "circuitBreaker over";
    }

    public String circuitBreaker_Fallback(int i){
        return "已出错，请稍后再试";
    }

}
