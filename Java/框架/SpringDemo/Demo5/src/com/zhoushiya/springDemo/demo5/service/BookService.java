package com.zhoushiya.springDemo.demo5.service;

import com.zhoushiya.springDemo.demo5.dao.IBookDao;
import com.zhoushiya.springDemo.demo5.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public int findCount(){
        return bookDao.selectCount();
    }

    @Override
    public Book findOne(int id) {
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void add(List<Book> books) {
        bookDao.add(books);
    }
}
