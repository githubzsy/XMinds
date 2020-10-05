package com.zhoushiya.springcloud.alibaba.storage.service.impl;

import com.zhoushiya.springcloud.alibaba.storage.dao.StorageDao;
import com.zhoushiya.springcloud.alibaba.storage.service.StorageService;
import org.springframework.stereotype.Service;

/**
 * @author zhoushiya
 * @date 2020/10/5 18:38
 */
@Service
public class StorageServiceImpl implements StorageService {

    private final StorageDao storageDao;

    public StorageServiceImpl(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    /**
     * 扣减库存
     * @param productId 产品Id
     * @param count 扣减数量
     */
    @Override
    public void decrease(long productId, int count) {
        storageDao.decrease(productId,count);
    }
}
