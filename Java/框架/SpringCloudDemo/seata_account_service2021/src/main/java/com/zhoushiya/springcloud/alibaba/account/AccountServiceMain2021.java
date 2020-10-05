package com.zhoushiya.springcloud.alibaba.account;

import com.zhoushiya.springcloud.alibaba.account.config.DataSourceProxyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * @author zhoushiya
 * @date 2020/10/5 19:15
 */
@EnableDiscoveryClient
@EnableFeignClients
// 取消数据源的自动创建，使用Seata对数据源进行代理
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
// 导入自定义数据源配置
@Import({DataSourceProxyConfig.class})
public class AccountServiceMain2021 {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceMain2021.class,args);
    }
}
