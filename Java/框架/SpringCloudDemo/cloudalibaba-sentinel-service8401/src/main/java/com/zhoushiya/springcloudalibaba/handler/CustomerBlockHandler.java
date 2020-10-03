package com.zhoushiya.springcloudalibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhoushiya.springcloud.entities.CommonResult;

/**
 * @author zhoushiya
 * @date 2020/10/3 15:29
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException1(BlockException blockException){
        return new CommonResult(4444,"客户自定义全局处理：1");
    }

    public static CommonResult handlerException2(BlockException blockException){
        return new CommonResult(4444,"客户自定义全局处理：2");
    }
}
