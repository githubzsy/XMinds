package com.zhoushiya.springDemo.demo5.service;

import com.zhoushiya.springDemo.demo5.entity.Book;

import java.util.List;

/**
 * @author zhoushiya
 * @date 2020/9/14 17:50
 */
public interface IBookService {
    void add(Book book);

    void update(Book book);

    int findCount();

    Book findOne(int id);

    List<Book> findAll();
}
