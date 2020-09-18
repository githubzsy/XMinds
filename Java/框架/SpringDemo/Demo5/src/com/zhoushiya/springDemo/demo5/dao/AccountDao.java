package com.zhoushiya.springDemo.demo5.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushiya
 * @date 2020/9/15 11:01
 */
@Repository
public class AccountDao implements IAccountDao {
    private final JdbcTemplate jdbcTemplate;

    public AccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void increaseMoney(int id, float money) {
        String sql="update account set money=money+? where id=?;";
        Object[] args={money,id};
        jdbcTemplate.update(sql,args);
    }
}
