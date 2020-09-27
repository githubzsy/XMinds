package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhoushiya
 * @date 2020/9/26 18:36
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaOrderMain81 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaOrderMain81.class, args);
    }
}
