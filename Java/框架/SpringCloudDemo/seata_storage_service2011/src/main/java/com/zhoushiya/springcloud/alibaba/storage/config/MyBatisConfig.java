package com.zhoushiya.springcloud.alibaba.storage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 设定mybatis dao层的扫描路径
 * @author zhoushiya
 * @date 2020/10/4 16:10
 */
@Configuration
@MapperScan({"com.zhoushiya.springcloud.alibaba.storage.dao"})
public class MyBatisConfig {
}
