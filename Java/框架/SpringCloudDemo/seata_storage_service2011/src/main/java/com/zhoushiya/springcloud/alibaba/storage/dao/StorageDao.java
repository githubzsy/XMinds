package com.zhoushiya.springcloud.alibaba.storage.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhoushiya
 * @date 2020/10/4 15:30
 */
@Mapper
public interface StorageDao {

    /**
     * 库存减少
     * @param productId 产品Id
     * @param count 减少多少
     */
    void decrease(@Param("productId") long productId,@Param("count") int count);
}
