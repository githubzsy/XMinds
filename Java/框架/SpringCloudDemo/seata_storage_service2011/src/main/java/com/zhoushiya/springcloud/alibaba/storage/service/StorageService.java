package com.zhoushiya.springcloud.alibaba.storage.service;

/**
 * @author zhoushiya
 * @date 2020/10/5 18:35
 */
public interface StorageService {
    /**
     * 扣减库存
     * @param productId 产品Id
     * @param count 扣减数量
     */
    void decrease(long productId, int count);
}
