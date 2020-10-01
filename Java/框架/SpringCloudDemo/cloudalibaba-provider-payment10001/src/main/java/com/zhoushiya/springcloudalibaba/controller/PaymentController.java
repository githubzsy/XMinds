package com.zhoushiya.springcloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zhoushiya
 * @date 2020/10/1 22:56
 */
@RestController
@RequestMapping("/payment")
@RefreshScope
public class PaymentController {

    @Value("${server.port}")
    public String port;

    @GetMapping("/uuid")
    public String uuid(){
        return port+":"+UUID.randomUUID().toString();
    }
}
