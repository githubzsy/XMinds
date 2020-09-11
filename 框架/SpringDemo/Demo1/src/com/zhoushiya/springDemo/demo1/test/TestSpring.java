package com.zhoushiya.springDemo.demo1.test;

import com.zhoushiya.springDemo.demo1.Book;
import com.zhoushiya.springDemo.demo1.Order;
import com.zhoushiya.springDemo.demo1.User;
import com.zhoushiya.springDemo.demo1.bean.Employee;
import com.zhoushiya.springDemo.demo1.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhoushiya
 * @date 2020/9/11 11:26
 */
public class TestSpring {

    @Test
    public void testAdd() {
        //1. 加载spring配置文件,classpath下的xml文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        //2. 获取配置创建的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();

        Book book = context.getBean("book", Book.class);
        System.out.println(book);

        Order order = context.getBean("order", Order.class);
        System.out.println(order);

    }

    @Test
    public void testServiceAndDao(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }

    @Test
    public void testInnerBean(){
        ApplicationContext context=new ClassPathXmlApplicationContext("bean3.xml");
        Employee employee = context.getBean("employee", Employee.class);
        System.out.println(employee);
    }
}
