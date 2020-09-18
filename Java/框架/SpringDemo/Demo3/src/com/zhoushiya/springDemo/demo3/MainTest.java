package com.zhoushiya.springDemo.demo3;

import com.zhoushiya.springDemo.demo3.config.SpringConfig;
import com.zhoushiya.springDemo.demo3.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhoushiya
 * @date 2020/9/13 13:52
 */
public class MainTest {
    @Test
    public void testService() {
        //1.加载配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserService userService = context.getBean("userService", IUserService.class);
        userService.add();
    }
}
