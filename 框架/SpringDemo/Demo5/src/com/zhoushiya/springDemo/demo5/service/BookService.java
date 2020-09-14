package com.zhoushiya.springDemo.demo5.service;

import com.zhoushiya.springDemo.demo5.dao.IBookDao;
import com.zhoushiya.springDemo.demo5.entity.Book;
import org.springframework.stereotype.Service;

/**
 * @author zhoushiya
 * @date 2020/9/14 17:50
 */
@Service
public class BookService implements IBookService {

    private final IBookDao bookDao;


    public BookService(IBookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void add(Book book) {
        bookDao.add(book);
    }
}
