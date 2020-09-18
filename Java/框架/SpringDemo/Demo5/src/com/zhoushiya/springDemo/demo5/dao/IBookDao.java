package com.zhoushiya.springDemo.demo5.dao;

import com.zhoushiya.springDemo.demo5.entity.Book;

import java.util.List;

/**
 * @author zhoushiya
 * @date 2020/9/14 17:50
 */
public interface IBookDao {
    void add(Book book);

    void update(Book book);


    int selectCount();

    Book findOne(int id);

    List<Book> findAll();

    void add(List<Book> books);
}
