package com.zhoushiya.springcloud.alibaba.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author zhoushiya
 * @date 2020/10/4 15:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private long userId;
    private long productId;
    private int count;
    private BigDecimal money;
    private int status;
}
