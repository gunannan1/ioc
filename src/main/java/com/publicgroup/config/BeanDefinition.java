package com.publicgroup.config;

import java.util.Map;


public interface BeanDefinition extends AttributeAccessor{

    //获得依赖BeanDefinition的名字
    Map getDepends();

    void addDepend(String name,String depend);

    String getDescription();

    Class<?> getBeanClass();

    void setBeanClass(Class<?> beanClass);

    boolean containsDepend(String name);
}
