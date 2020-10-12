package org.example.mymapper;

import org.example.mymapper.config.MyConfig;
import org.example.mymapper.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhoushiya
 * @date 2020/10/11 20:05
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.test();
    }
}
