package com.zhoushiya.springDemo.demo5.dao;

import com.zhoushiya.springDemo.demo5.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushiya
 * @date 2020/9/14 17:50
 */
@Repository
public class BookDao implements IBookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Book book) {
        String sql="insert into book values (?,?)";
        System.out.println(jdbcTemplate.update(sql,book.getId(),book.getName()));
    }
}
