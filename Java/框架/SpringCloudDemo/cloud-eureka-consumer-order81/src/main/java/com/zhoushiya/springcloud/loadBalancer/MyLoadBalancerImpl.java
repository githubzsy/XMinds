package com.zhoushiya.springcloud.loadBalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoushiya
 * @date 2020/9/27 21:42
 */
@Component
public class MyLoadBalancerImpl implements MyLoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 要对哪个服务起作用
     */
    private static final String SERVICE_ID = "provider-payment";

    private List<ServiceInstance> serviceInstances;

    public MyLoadBalancerImpl(DiscoveryClient discoveryClient) {
        this.serviceInstances = discoveryClient.getInstances(SERVICE_ID);
    }

    public final int getAndIncrement() {
        // 当前访问次数
        int current;
        // 下一次访问次数
        int next;
        do {
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : (current + 1);
        } while (!atomicInteger.compareAndSet(current, next));
        System.out.println("******next:" + next);
        return next;
    }

    /**
     * 获取当前负载均衡的微服务
     * @return
     */
    @Override
    public ServiceInstance instance() {
        // 当前服务下标
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
