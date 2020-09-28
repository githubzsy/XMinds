package com.zhoushiya.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.zhoushiya.springcloud.service.PaymentService;

/**
 * @author zhoushiya
 * @date 2020/9/26 18:39
 */
@RestController
@Slf4j
/**
 * 全局允许超时3秒
 * 降级处理：global_Fallback，注意global_Fallback既不能有参数，返回值也要能兼容所有方法的返回值
 */
@DefaultProperties(defaultFallback = "global_Fallback", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
public class OrderController {
    private final PaymentService paymentService;

    public OrderController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/consumer/payment/waitTime2/{millis}")
    // HystrixCommand不能省略，表示使用默认的faultFallback
    @HystrixCommand
    public String waitTime2(@PathVariable("millis") long millis) {
        return paymentService.waitTime(millis);
    }

    /**
     * 允许超时时间1秒
     * @param millis
     * @return
     */
    @GetMapping("/consumer/payment/waitTime/{millis}")
    @HystrixCommand(fallbackMethod = "waitTime_Fallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String waitTime(@PathVariable("millis") long millis) {
        return paymentService.waitTime(millis);
    }

    /**
     * waitTime的fallback处理
     *
     * @param millis
     * @return
     */
    public String waitTime_Fallback(long millis) {
        return "controller_waitTime:对方系统繁忙，或者我现在很忙，请稍后再试";
    }

    /**
     * 全局fallback处理
     * 不能写参数
     * 返回值要是api的返回值相兼容：必须是其本身或者子类
     * @return
     */
    public String global_Fallback() {
        return "controller_global:对方系统繁忙，或者我现在很忙，请稍后再试。";
    }
}
