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
public class PayZKMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PayZKMain8004.class,args);
    }
}
