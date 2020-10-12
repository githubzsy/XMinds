package org.example.mymapper.config;

import org.example.mymapper.annotation.MyScan;
import org.example.mymapper.registrar.MyBeanDefinitionRegistrar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhoushiya
 * @date 2020/10/11 19:46
 */
@Configuration
@ComponentScan("org.example.mymapper")
// 导入类，执行里面的方法，实际上会执行
@Import(MyBeanDefinitionRegistrar.class)
// 定义包扫描路径，后面在{@code MyBeanDefinitionRegistrar}进行处理
@MyScan("org.example.mymapper.mapper")
public class MyConfig {
}
