package com.zhoushiya.springcloud.controller;

import com.zhoushiya.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhoushiya
 * @date 2020/9/26 18:39
 */
@RestController
public class OrderController {
    /** 单机配置
     public static final String PAYMENT_URL = "http://localhost:8001";
     **/

    /**
     * 集群配置：http://微服务名称
     * zookeeper大小写敏感，注意大小写
     */
    private static final String PAYMENT_URL = "http://provider-payment";

    private final RestTemplate restTemplate;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consumer/payment/uuid")
    public String uuid(Payment payment) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/uuid", String.class);
    }
}
