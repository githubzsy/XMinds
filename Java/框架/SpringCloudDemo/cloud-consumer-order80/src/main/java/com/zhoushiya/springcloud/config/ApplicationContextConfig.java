package com.zhoushiya.springcloud.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhoushiya
 * @date 2020/9/26 18:42
 */
@SpringBootConfiguration
public class ApplicationContextConfig {
    /**
     * 获取一个RestTemplate
     * @return
     */
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
