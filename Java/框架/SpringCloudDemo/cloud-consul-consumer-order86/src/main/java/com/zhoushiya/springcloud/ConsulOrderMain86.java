package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhoushiya
 * @date 2020/9/27 18:46
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulOrderMain86 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulOrderMain86.class,args);
    }
}
