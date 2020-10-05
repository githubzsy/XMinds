package com.zhoushiya.springcloud.alibaba.order.service;

import com.zhoushiya.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 账户微服务
 * @author zhoushiya
 * @date 2020/10/4 15:48
 */
@FeignClient("seata-account-service")
public interface AccountService {
    @PostMapping("/account/decrease/{userId}/{money}")
    CommonResult decrease(@PathVariable("userId") long userId, @PathVariable("money") BigDecimal money);
}
