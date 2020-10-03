package com.zhoushiya.springcloudalibaba.controller;

import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author zhoushiya
 * @date 2020/10/3 18:13
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Value("${server.port}")
    public String serverPort;

    public static HashMap<Long, Payment> hashMap=new HashMap<>();
    static{
        hashMap.put(1l,new Payment(1l,"aaa"));
        hashMap.put(2l,new Payment(2l,"bbb"));
        hashMap.put(3l,new Payment(3l,"ccc"));
    }

    @GetMapping(value = "/{id}")
    public CommonResult<Payment> get(@PathVariable("id") long id){
        return new CommonResult<>(200,"from mysql,serverPort: "+serverPort,hashMap.get(id));
    }

}
