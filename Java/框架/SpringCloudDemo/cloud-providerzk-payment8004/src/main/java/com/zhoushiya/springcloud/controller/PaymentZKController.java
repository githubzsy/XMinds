package com.zhoushiya.springcloud.controller;

import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import com.zhoushiya.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author zhoushiya
 * @date 2020/9/26 17:27
 */
@RestController
@Slf4j
public class PaymentZKController {
    @Value("${server.port}")
    private String serverPort;

    private final DiscoveryClient discoveryClient;

    private final PaymentService paymentService;

    public PaymentZKController(DiscoveryClient discoveryClient, PaymentService paymentService) {
        this.discoveryClient = discoveryClient;
        this.paymentService = paymentService;
    }

    @GetMapping("/payment/zk")
    public String zk() {
        return "******spring cloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID();
    }

}
