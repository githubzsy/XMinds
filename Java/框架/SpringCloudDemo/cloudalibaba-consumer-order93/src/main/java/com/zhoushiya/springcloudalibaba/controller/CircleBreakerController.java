package com.zhoushiya.springcloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloudalibaba.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhoushiya
 * @date 2020/10/3 18:28
 */
@RestController
@RequestMapping("/order")
public class CircleBreakerController {
    @Value("${service-url.nacos-payment-provider}")
    public String serviceUrl;

    private final RestTemplate restTemplate;

    private final PaymentService paymentService;


    public CircleBreakerController(RestTemplate restTemplate, PaymentService paymentService) {
        this.restTemplate = restTemplate;
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    @SentinelResource(value = "/order/{id}",fallback = "fallBack",blockHandler = "blockHandler")
    public CommonResult get(@PathVariable("id") long id){
        return restTemplate.getForObject(serviceUrl+"/payment/"+id,CommonResult.class);
    }

    public CommonResult fallBack(long id,Throwable e){
        return new CommonResult(500,"程序出现异常:"+e.getMessage());
    }

    public CommonResult blockHandler(long id, BlockException blockException){
        return new CommonResult(429,"您的访问过快，请稍后再试:"+blockException.getMessage());
    }

    /**
     * 使用feign来调用
     * @param id
     * @return
     */
    @GetMapping("/feign/{id}")
    @SentinelResource(value = "/order/feignGet/{id}")
    public CommonResult feignGet(@PathVariable("id") long id){
        return paymentService.get(id);
    }
}
