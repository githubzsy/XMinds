package com.zhoushiya.springcloud.service;

import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author zhoushiya
 * @date 2020/9/28 19:27
 */
@Component
public class PaymentServiceFallbackImpl implements PaymentService  {
    @Override
    public String waitTime(long millis) {
        return "service:waitTime exception";
    }
}
