package com.zhoushiya.springcloud.controller;

import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhoushiya
 * @date 2020/9/26 18:39
 */
@RestController
@Slf4j
public class OrderController {
    /** 单机配置
    public static final String PAYMENT_URL = "http://localhost:8001";
     **/

    /**
     * 集群配置：http://微服务名称
     */
    private static final String PAYMENT_URL = "http://provider-payment";

    private final RestTemplate restTemplate;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/uuid")
    public String uuid() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/uuid", String.class);
    }

    @GetMapping("/consumer/payment/getEntity/{id}")
    public ResponseEntity<CommonResult> getPaymentEntity(@PathVariable Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        log.info("******entity:"+entity);
        return entity;
    }
}
