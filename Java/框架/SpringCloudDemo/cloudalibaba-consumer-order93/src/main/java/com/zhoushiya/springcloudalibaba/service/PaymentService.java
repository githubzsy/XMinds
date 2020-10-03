package com.zhoushiya.springcloudalibaba.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhoushiya
 * @date 2020/10/3 22:12
 */
@FeignClient(value = "nacos-payment-provider",fallback = PaymentServiceImpl.class)
public interface PaymentService {
    /**
     * 服务端地址
     * @param id
     * @return
     */
    @GetMapping("/payment/{id}")
    CommonResult<Payment> get(@PathVariable("id") long id);
}
