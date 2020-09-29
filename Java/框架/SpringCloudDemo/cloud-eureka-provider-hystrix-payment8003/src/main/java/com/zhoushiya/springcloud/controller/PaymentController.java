package com.zhoushiya.springcloud.controller;

import com.zhoushiya.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushiya
 * @date 2020/9/28 15:04
 */
@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment/waitTime/{millis}")
    public String waitTime(@PathVariable("millis") long millis) throws InterruptedException {
        return paymentService.waitTime(millis);
    }

    @GetMapping("/payment/circuit/{id}")
    public String circuit(@PathVariable("id") int id){
        return paymentService.circuitBreaker(id);
    }
}
