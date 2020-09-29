package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhoushiya
 * @date 2020/9/29 17:07
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaGatewayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaGatewayMain9527.class,args);
    }
}
