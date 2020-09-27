package com.zhoushiya.springcloud.service;

import com.zhoushiya.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhoushiya
 * @date 2020/9/26 17:23
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
