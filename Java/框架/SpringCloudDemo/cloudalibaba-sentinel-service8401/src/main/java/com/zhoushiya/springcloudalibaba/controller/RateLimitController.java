package com.zhoushiya.springcloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sun.deploy.security.BlockedDialog;
import com.sun.deploy.security.BlockedException;
import com.zhoushiya.springcloud.entities.CommonResult;
import com.zhoushiya.springcloud.entities.Payment;
import com.zhoushiya.springcloudalibaba.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushiya
 * @date 2020/10/3 15:09
 */
@RestController
@RequestMapping("/rateLimit")
public class RateLimitController {

    /**
     * 演示@SentinelResource
     * @param runtimeException 是否要出现runtime异常
     * @return
     */
    @GetMapping("/byResource/{runtimeException}")
    @SentinelResource(value="byResource",blockHandler = "handleException",fallback = "fallBack")
    public CommonResult byResource(@PathVariable("runtimeException") boolean runtimeException){
        if(runtimeException){
            int i=1/0;
        }
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(boolean runtimeException,BlockException blockException){
        return new CommonResult(444, blockException.getClass().getCanonicalName()+"\t 服务不可用");
    }

    public CommonResult fallBack(boolean runtimeException){
        return new CommonResult(444,"出现了500的异常");
    }

    /**
     * 使用全局处理BlockException
     * @return
     */
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按客户自定义",new Payment(2020l,"serial03"));
    }
}
