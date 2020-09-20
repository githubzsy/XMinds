package com.zhoushiya.springDemo.demo5.test;

import com.zhoushiya.springDemo.demo5.config.TXConfig;
import com.zhoushiya.springDemo.demo5.service.IAccountService;
import com.zhoushiya.springDemo.demo5.service.IBookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

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

    @Test
    public void testTransfer(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean1.xml");
        IAccountService accountService = classPathXmlApplicationContext.getBean("accountService", IAccountService.class);
        accountService.transfer(1,2,100);
    }

    /**
     * 测试完全注解开发
     */
    @Test
    public void testConfig(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TXConfig.class);
        IAccountService accountService = annotationConfigApplicationContext.getBean("accountService", IAccountService.class);
        accountService.transfer(1,2,100);
    }

    /**
     * 函数式风格
     */
    @Test
    public void textGenericApplicationContext(){
        GenericApplicationContext context = new GenericApplicationContext();
        //2.清空
        context.refresh();
        //3.注册bean
        context.registerBean(UserLog.class,()->new UserLog());
        //4.获取bean
        UserLog bean = context.getBean("com.zhoushiya.springDemo.demo5.test.UserLog", UserLog.class);
        System.out.println(bean);
    }
}
