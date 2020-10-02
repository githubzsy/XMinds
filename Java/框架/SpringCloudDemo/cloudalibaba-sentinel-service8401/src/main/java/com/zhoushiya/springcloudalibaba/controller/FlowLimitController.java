package com.zhoushiya.springcloudalibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushiya
 * @date 2020/10/2 23:14
 */
@RestController
@RequestMapping("/flowLimit")
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "testA";
    }
}
