package org.example.mymapper.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 定义Mapper的扫描路径
 * @author zhoushiya
 * @date 2020/10/11 20:32
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyScan {
    /**
     * 包扫描路径
     * @return
     */
    String value() default "";
}
