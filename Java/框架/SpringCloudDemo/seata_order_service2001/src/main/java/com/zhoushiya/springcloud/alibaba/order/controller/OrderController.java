package com.zhoushiya.springcloud.alibaba.order.controller;

import com.zhoushiya.springcloud.alibaba.order.domain.Order;
import com.zhoushiya.springcloud.alibaba.order.service.OrderService;
import com.zhoushiya.springcloud.entities.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushiya
 * @date 2020/10/4 16:00
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 创建订单
     * @param order
     * @return
     */
    @PostMapping
    public ResponseEntity<CommonResult<Order>> create(@RequestBody Order order) {
        orderService.create(order);
        return new ResponseEntity<>(new CommonResult(HttpStatus.OK.value(), "订单创建成功", order), HttpStatus.OK);
    }
}
