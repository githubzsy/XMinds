package com.zhoushiya.springcloud.loadBalancer;

import org.springframework.cloud.client.ServiceInstance;


/**
 * @author zhoushiya
 * @date 2020/9/27 21:41
 */
public interface MyLoadBalancer {
    ServiceInstance instance();
}
