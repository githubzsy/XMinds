package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhoushiya
 * @date 2020/10/1 13:38
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaConsumerStreamRabbitMQMain8811 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerStreamRabbitMQMain8811.class,args);
    }
}
