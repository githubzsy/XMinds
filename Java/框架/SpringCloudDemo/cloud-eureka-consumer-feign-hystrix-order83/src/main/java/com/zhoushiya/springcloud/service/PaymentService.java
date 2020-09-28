package com.zhoushiya.springcloud.service;

import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhoushiya
 * @date 2020/9/28 12:41
 */
@Component
// 配置接口整体fallback实现类为PaymentServiceFallbackImpl
@FeignClient(value = "provider-hystrix-payment",fallback = PaymentServiceFallbackImpl.class)
public interface PaymentService {
    /**
     * 模拟超时
     * @return
     */
    @GetMapping("/payment/waitTime/{millis}")
    String waitTime(@PathVariable("millis") long millis);
}
