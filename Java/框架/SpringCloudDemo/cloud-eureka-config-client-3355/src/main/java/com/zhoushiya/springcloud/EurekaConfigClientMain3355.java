package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhoushiya
 * @date 2020/9/30 16:41
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaConfigClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConfigClientMain3355.class,args);
    }
}
