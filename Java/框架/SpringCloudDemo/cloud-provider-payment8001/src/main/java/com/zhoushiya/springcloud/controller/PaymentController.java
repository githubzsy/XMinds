package com.zhoushiya.springcloud.controller;

import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import com.zhoushiya.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoushiya
 * @date 2020/9/26 17:27
 */
@RestController
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果:" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功", result);
        } else {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:"+payment);
        if(payment!=null){
            return new CommonResult(200,"查询成功",payment);
        }
        else{
            return new CommonResult(404,"查询失败",null);
        }
    }

}
