package com.zhoushiya.springDemo.demo5.test;

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
//        Book book=new Book();
//        book.setId(1);
//        book.setName("java书记");
//        bookService.add(book);

//        List<Book> books=new LinkedList<>();
//        books.add(new Book(4,"book 4"));
//        books.add(new Book(5,"book 5"));
//        bookService.add(books);
        System.out.println(bookService.findCount());
        System.out.println(bookService.findAll());
    }
}
