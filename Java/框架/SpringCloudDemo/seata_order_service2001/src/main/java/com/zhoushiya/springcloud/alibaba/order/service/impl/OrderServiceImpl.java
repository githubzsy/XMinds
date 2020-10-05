package com.zhoushiya.springcloud.alibaba.order.service.impl;

import com.zhoushiya.springcloud.alibaba.order.dao.OrderDao;
import com.zhoushiya.springcloud.alibaba.order.domain.Order;
import com.zhoushiya.springcloud.alibaba.order.service.AccountService;
import com.zhoushiya.springcloud.alibaba.order.service.OrderService;
import com.zhoushiya.springcloud.alibaba.order.service.StorageService;
import com.zhoushiya.springcloud.entities.CommonResult;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhoushiya
 * @date 2020/10/4 15:46
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    private final StorageService storageService;

    private final AccountService accountService;

    public OrderServiceImpl(OrderDao orderDao, StorageService storageService, AccountService accountService) {
        this.orderDao = orderDao;
        this.storageService = storageService;
        this.accountService = accountService;
    }

    /**
     * 下单并且支付
     * 下订单->减库存->减余额->改状态
     * @param order
     */
    @Override
    @GlobalTransactional
    public void create(Order order) {
        log.info("-----开始创建订单");
        orderDao.create(order);
        log.info("-----订单微服务开始调用库存微服务，做扣减");
        CommonResult decrease = storageService.decrease(order.getProductId(), order.getCount());
        log.info("-----订单微服务开始调用账户微服务，做扣减");
        decrease = accountService.decrease(order.getUserId(),order.getMoney());
        log.info("-----更新订单状态");
        orderDao.updateToSuccess(order.getUserId(),order.getId());
        order.setStatus(1);
    }
}
