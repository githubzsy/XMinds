package com.zhoushiya.springcloud.alibaba.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author zhoushiya
 * @date 2020/10/5 19:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private long id;
    private long userId;
    private BigDecimal total;
    private BigDecimal used;
    private BigDecimal residue;
}
