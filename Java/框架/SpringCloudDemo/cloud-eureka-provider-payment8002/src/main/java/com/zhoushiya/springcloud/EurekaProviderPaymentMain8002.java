package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhoushiya
 * @date 2020/9/26 14:57
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class EurekaProviderPaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderPaymentMain8002.class, args);
    }
}
