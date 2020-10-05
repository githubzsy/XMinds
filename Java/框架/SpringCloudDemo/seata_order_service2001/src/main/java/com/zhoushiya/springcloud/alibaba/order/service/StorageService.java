package com.zhoushiya.springcloud.alibaba.order.service;

import com.zhoushiya.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 库存微服务
 * @author zhoushiya
 * @date 2020/10/4 15:47
 */
@FeignClient(value= "seata-storage-service")
@RequestMapping("/storage")
public interface StorageService {
    @PostMapping("/decrease/{productId}/{count}")
    CommonResult decrease(@PathVariable("productId") long productId, @PathVariable("count") int count);
}
