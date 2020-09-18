package com.zhoushiya.springDemo.demo2.factoryBean;

import com.zhoushiya.springDemo.demo2.collectionType.Course;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author zhoushiya
 * @date 2020/9/11 19:38
 */
public class CourseBeanFactory implements FactoryBean<Course>{

    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setName("语文");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return Course.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
