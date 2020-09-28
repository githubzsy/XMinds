package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhoushiya
 * @date 2020/9/28 14:43
 */
@SpringBootApplication
@EnableEurekaClient
// 启用服务降级：断路器
@EnableCircuitBreaker
public class EurekaProviderHystrixPaymentMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderHystrixPaymentMain8003.class,args);
    }
}
