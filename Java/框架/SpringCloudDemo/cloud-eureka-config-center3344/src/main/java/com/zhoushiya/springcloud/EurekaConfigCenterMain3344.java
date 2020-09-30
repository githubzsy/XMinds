package com.zhoushiya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zhoushiya
 * @date 2020/9/30 15:46
 */
@SpringBootApplication
@EnableConfigServer
public class EurekaConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConfigCenterMain3344.class,args);
    }
}
