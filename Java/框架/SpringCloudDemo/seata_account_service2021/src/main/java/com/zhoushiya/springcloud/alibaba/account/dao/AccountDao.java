package com.zhoushiya.springcloud.alibaba.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author zhoushiya
 * @date 2020/10/5 19:03
 */
@Mapper
public interface AccountDao {
    /**
     * 扣减账户金额
     * @param userId 用户Id
     * @param money 金额数目
     */
    void decrease(@Param("userId") long userId,@Param("money") BigDecimal money);
}
