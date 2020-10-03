package com.zhoushiya.springcloudalibaba.service;

import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author zhoushiya
 * @date 2020/10/3 22:15
 */
@Component
public class PaymentServiceImpl implements PaymentService {
    /**
     * 编写服务降级方法
     * @param id
     * @return
     */
    @Override
    public CommonResult<Payment> get(long id) {
        return new CommonResult<>(500,"PaymentServiceImpl,服务降级");
    }
}
