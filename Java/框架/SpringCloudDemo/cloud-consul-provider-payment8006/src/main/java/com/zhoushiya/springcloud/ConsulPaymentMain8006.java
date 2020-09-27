package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhoushiya
 * @date 2020/9/27 18:31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulPaymentMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentMain8006.class, args);
    }
}
