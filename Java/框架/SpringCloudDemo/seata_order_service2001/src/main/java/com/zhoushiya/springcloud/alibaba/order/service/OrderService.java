package com.zhoushiya.springcloud.alibaba.order.service;

import com.zhoushiya.springcloud.alibaba.order.domain.Order;

/**
 * 订单服务
 * @author zhoushiya
 * @date 2020/10/4 15:45
 */
public interface OrderService {
    /**
     * 下单并且支付
     * @param order
     */
    void create(Order order);
}
