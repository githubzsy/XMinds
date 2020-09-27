package com.zhoushiya.springcloud;

import com.zhoushiya.loadBalanceRule.config.RuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * @author zhoushiya
 * @date 2020/9/26 18:36
 */
@SpringBootApplication
@EnableEurekaClient
/**
 * 表示访问微服务provider-payment时候，使用RuleConfig配置中的规则
 */
//@RibbonClient(name = "provider-payment",configuration = RuleConfig.class)
public class EurekaOrderMain81 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaOrderMain81.class, args);
    }
}
