package com.zhoushiya.springcloud.service;

import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhoushiya
 * @date 2020/9/28 12:41
 */
@Component
// 寻找微服务provider-payment
@FeignClient("provider-payment")
public interface PaymentService {

    /**
     * 寻找微服务方法getPaymentById
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 模拟超时
     * @return
     */
    @GetMapping("/payment/timeout/{millis}")
    String timeout(@PathVariable("millis") int millis);
}
