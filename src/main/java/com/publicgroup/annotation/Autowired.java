package com.publicgroup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kenny
 * 本接口既有类似spring的Autowired的作用，同时又充当@Value注解的作用
 * 允许value()值为8种基本类型，如果转换失败会成为null
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    public String value() default "";
}
