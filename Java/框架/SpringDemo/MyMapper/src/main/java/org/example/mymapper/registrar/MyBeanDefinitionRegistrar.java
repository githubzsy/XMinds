package org.example.mymapper.registrar;

import org.example.mymapper.annotation.MyMapperScan;
import org.example.mymapper.annotation.MyScan;
import org.example.mymapper.factorybean.MyFactoryBean;
import org.example.mymapper.mapper.OrderMapper;
import org.example.mymapper.mapper.UserMapper;
import org.example.mymapper.utils.ClassScanner;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoushiya
 * @date 2020/10/11 19:48
 */
public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 将MyFactoryBean注册到IOC容器中（生成Mapper动态代理对象的bean）
     * 实际上是注册不同的BeanDefinition到BeanFactory中，后期根据不同mapperName，生成不同的代理对象
     *
     * @param importingClassMetadata
     * @param registry
     * @param importBeanNameGenerator
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {

        // 拿到MyScan的注解
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyScan.class.getName());

        String value = (String) annotationAttributes.get("value");

        ClassScanner classScanner = new ClassScanner();
        classScanner.scanning(value, false);
        Map<String, Class<?>> classes = classScanner.getClasses();

        for (Class<?> mapper : classes.values()) {
            MyMapperScan annotation = mapper.getAnnotation(MyMapperScan.class);
            if (annotation == null) {
                continue;
            }

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

            beanDefinition.setBeanClass(MyFactoryBean.class);
            // 对MyFactoryBean构造函数中加入Mapper类型
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(mapper);

            // 将beanDefinition注册到BeanFactory中
            // 表示这个bean的value是mapperName，对象是由MyFactoryBean来生成的
            registry.registerBeanDefinition(mapper.getSimpleName(), beanDefinition);
        }


    }
}
