package com.zhoushiya.springcloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoushiya
 * @date 2020/10/2 23:14
 */
@RestController
@RequestMapping("/flowLimit")
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        // Thread.sleep(800);
        return "testA";
    }

    @GetMapping("/testB")
    public String testB() {
        System.out.println(Thread.currentThread().getName());
        return "testB";
    }

    @GetMapping("/waitTime/{millis}")
    public String waitTime(@PathVariable("millis") long millis) throws InterruptedException {
        Thread.sleep(millis);
        return "waitTime";
    }

    @GetMapping("/exception/{exceptionOccurred}")
    public String throwException(@PathVariable("exceptionOccurred") boolean exceptionOccurred){
        if(exceptionOccurred) {
            int i = 1 / 0;
        }
        return "throwException";
    }

    @GetMapping("/testHotKey")
    // 设定热点key过滤，控制台违规兜底方法为deal_testHotKey
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,@RequestParam(value = "p2",required = false) String p2) {
        return "testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException blockException){
        return "触发服务降级：deal_testHotKey";
    }
}
