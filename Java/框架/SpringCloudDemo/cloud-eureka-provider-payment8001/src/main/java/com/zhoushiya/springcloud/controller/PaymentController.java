package com.zhoushiya.springcloud.controller;

import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import com.zhoushiya.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author zhoushiya
 * @date 2020/9/26 17:27
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    private final DiscoveryClient discoveryClient;

    private final PaymentService paymentService;

    public PaymentController(DiscoveryClient discoveryClient, PaymentService paymentService) {
        this.discoveryClient = discoveryClient;
        this.paymentService = paymentService;
    }

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果:" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(404, "查询失败", null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        /**
         * 所有微服务
         */
        List<String> services = discoveryClient.getServices();
        services.forEach(log::info);

        /**
         * 目标微服务实例
         */
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(instance -> log.info("******"+instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri()));

        return this.discoveryClient;
    }

    @GetMapping("/payment/uuid")
    public String uuid() {
        return "******spring cloud with consul:" + serverPort + "\t" + UUID.randomUUID();
    }

}
