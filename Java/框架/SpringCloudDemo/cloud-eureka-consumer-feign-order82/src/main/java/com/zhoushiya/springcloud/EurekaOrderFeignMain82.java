package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhoushiya
 * @date 2020/9/26 18:36
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class EurekaOrderFeignMain82 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaOrderFeignMain82.class, args);
    }
}
