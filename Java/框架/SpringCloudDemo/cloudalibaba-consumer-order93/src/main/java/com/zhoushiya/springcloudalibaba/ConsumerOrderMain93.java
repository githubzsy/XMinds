package com.zhoushiya.springcloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhoushiya
 * @date 2020/10/3 18:26
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ConsumerOrderMain93 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain93.class,args);
    }
}
