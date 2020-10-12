package org.example.mymapper.processor;

import org.example.mymapper.factorybean.MyFactoryBean;
import org.example.mymapper.mapper.OrderMapper;
import org.example.mymapper.mapper.UserMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoushiya
 * @date 2020/10/11 18:39
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    /**
     * BeanFactory后置处理器，将UserMapper和OrderMapper加入到bean管理
     *
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
