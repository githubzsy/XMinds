package com.zhoushiya.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushiya
 * @date 2020/9/30 16:43
 */
@RestController
// 实现刷新功能
@RefreshScope
public class ConfigClientController {

    /**
     * 从配置中心读取config.info内容
     */
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
