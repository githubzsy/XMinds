package com.zhoushiya.springcloud.alibaba.order.dao;

import com.zhoushiya.springcloud.alibaba.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhoushiya
 * @date 2020/10/4 15:30
 */
@Mapper
public interface OrderDao {
    /**
     * 下订单
     * @param order
     */
    void create(Order order);

    /**
     * 修改订单状态到成功
     * @param userId 用户id
     * @param id 订单id
     */
    void updateToSuccess(@Param("userId") long userId,@Param("id") long id);
}
