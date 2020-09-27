package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhoushiya
 * @date 2020/9/27 13:41
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PayMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PayMain8004.class,args);
    }
}
