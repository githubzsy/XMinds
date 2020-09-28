package com.zhoushiya.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zhoushiya
 * @date 2020/9/28 13:38
 */
@Configuration
public class FeignConfig {
    /**
     * 配置为详细日志
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel(){
       return Logger.Level.FULL;
    }
}
