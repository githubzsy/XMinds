package com.zhoushiya.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zhoushiya
 * @date 2020/9/26 17:27
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    private final DiscoveryClient discoveryClient;

    public PaymentController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/payment/uuid")
    public String uuid() {
        return "******spring cloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID();
    }

}
