package com.zhoushiya.springDemo.demo2.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhoushiya.springDemo.demo2.autowire.Employee;
import com.zhoushiya.springDemo.demo2.bean.Order;
import com.zhoushiya.springDemo.demo2.collectionType.Book;
import com.zhoushiya.springDemo.demo2.collectionType.Course;
import com.zhoushiya.springDemo.demo2.collectionType.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhoushiya
 * @date 2020/9/11 17:50
 */
public class MainTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }

    @Test
    public void testUtil(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Book book = context.getBean("book", Book.class);
        Book book2 = context.getBean("book", Book.class);
        System.out.println(book.equals(book2));
    }

    @Test
    public void testFactoryBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        Course course = context.getBean("courseBeanFactory", Course.class);
        System.out.println(course);
    }

    @Test
    public void testLifetime(){
        // 可以直接使用ApplicationContext实现类ClassPathXmlApplicationContext
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
        Order order = context.getBean("order", Order.class);
        System.out.println("获取创建bean实例");
        context.close();
    }

    @Test
    public void testAutowire(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean5.xml");
        Employee employee = context.getBean("employee", Employee.class);
        System.out.println(employee);
    }

    @Test
    public void testOuterProperties(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean6.xml");
        DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);

    }
}
