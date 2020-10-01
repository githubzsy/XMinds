package com.zhoushiya.springcloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhoushiya
 * @date 2020/10/1 22:54
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderPaymentMain10002 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentMain10002.class,args);
    }
}
