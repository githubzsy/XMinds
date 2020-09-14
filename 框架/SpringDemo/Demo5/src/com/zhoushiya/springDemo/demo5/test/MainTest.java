package com.zhoushiya.springDemo.demo5.test;

import com.zhoushiya.springDemo.demo5.entity.Book;
import com.zhoushiya.springDemo.demo5.service.IBookService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhoushiya
 * @date 2020/9/14 18:01
 */
public class MainTest {
    @Test
    public void testAdd(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean1.xml");
        IBookService bookService = classPathXmlApplicationContext.getBean("bookService", IBookService.class);
        Book book=new Book();
        book.setId(1);
        book.setName("java书记");
        bookService.add(book);
    }
}
