package com.zhoushiya.loadBalanceRule.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoushiya
 * @date 2020/9/27 21:14
 */
@Configuration
public class RuleConfig {
    @Bean
    public IRule myRule(){
        // 随机
        return new RandomRule();
    }
}
