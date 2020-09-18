package com.zhoushiya.springDemo.demo5.dao;

import com.zhoushiya.springDemo.demo5.entity.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoushiya
 * @date 2020/9/14 17:50
 */
@Repository
public class BookDao implements IBookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Book book) {
        String sql = "insert into book values (?,?)";
        System.out.println(jdbcTemplate.update(sql, book.getId(), book.getName()));
    }

    @Override
    public void update(Book book) {
        String sql = "update book set name=? where id=?";
        Object[] args = {book.getName(), book.getId()};
        System.out.println(jdbcTemplate.update(sql, args));
    }

    @Override
    public int selectCount() {
        String sql = "select count(*) from book;";
        return jdbcTemplate.queryForObject(sql, int.class);
    }

    @Override
    public Book findOne(int id) {
        String sql = "select id,name from book where id = ?;";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
    }

    @Override
    public List<Book> findAll() {
        String sql = "select id,name from book;";
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }

    @Override
    public void add(List<Book> books) {
        String sql = "insert into book values (?,?)";
        List<Object[]> batchArgs = books.stream().map(book -> new Object[]{book.getId(), book.getName()}).collect(Collectors.toList());
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}
