package com.zhoushiya.springcloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhoushiya
 * @date 2020/10/1 23:28
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private final RestTemplate restTemplate;

    @Value("${service-url.provider-payment}")
    public String providerUrl;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/payment/uuid")
    public String uuid(){
        return restTemplate.getForObject(providerUrl+"/payment/uuid",String.class);
    }
}
