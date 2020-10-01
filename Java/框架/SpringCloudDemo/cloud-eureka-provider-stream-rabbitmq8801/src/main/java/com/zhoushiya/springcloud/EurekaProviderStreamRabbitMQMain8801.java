package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhoushiya
 * @date 2020/10/1 12:18
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaProviderStreamRabbitMQMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderStreamRabbitMQMain8801.class,args);
    }
}
