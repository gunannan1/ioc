package com.publicgroup.config;

import java.util.List;
import java.util.Map;


/**
 * @author Kenny
 * 本类为Bean的构造信息
 * 继承AttributeAccessor拥有处理基础属性的能力
 * 通过BeanDeifinition从文件或注解中读取，再用于BeanFactory在创建bean
 */
public interface BeanDefinition extends AttributeAccessor{


    /**
     * @return 依赖的bean的Name列表
     * 获取所有依赖的bean
     */
    List getDepends();

    /**
     * @param depend 依赖的bean的名字
     * 添加一个依赖的bean
     */
    void addDepend(String depend);

    /**
     * @return 返回bean信息描述，暂且返回bean的class的simpleName
     */
    String getDescription();

    /**
     * @return bean的Class对象
     */
    Class<?> getBeanClass();

    /**
     * @param beanClass 设置bean的class对象
     */
    void setBeanClass(Class<?> beanClass);

    /**
     * @param name Bean的名字
     * @return 是否依赖于名字为name的bean的
     */
    boolean containsDepend(String name);
}
