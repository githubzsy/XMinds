package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhoushiya
 * @date 2020/9/26 18:36
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class EurekaConsumerFeignHystrixOrderMain83 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerFeignHystrixOrderMain83.class, args);
    }
}
