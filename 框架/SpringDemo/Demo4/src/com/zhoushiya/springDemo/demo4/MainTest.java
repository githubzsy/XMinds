package com.zhoushiya.springDemo.demo4;

import com.zhoushiya.springDemo.demo4.aopAnnotation.User;
import com.zhoushiya.springDemo.demo4.aopXml.Book;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhoushiya
 * @date 2020/9/14 11:53
 */
public class MainTest {
    @Test
    public void testAop(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean1.xml");
        User user = classPathXmlApplicationContext.getBean("user", User.class);
        user.add();
    }

    @Test
    public void testAopXml(){
        new ClassPathXmlApplicationContext("bean2.xml").getBean("book", Book.class).buy();
    }
}
