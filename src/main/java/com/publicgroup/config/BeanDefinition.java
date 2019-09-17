package com.publicgroup.config;

import java.util.List;
import java.util.Map;


public interface BeanDefinition extends AttributeAccessor{

    //获得依赖BeanDefinition的名字
    List getDepends();

    void addDepend(String depend);

    String getDescription();

    Class<?> getBeanClass();

    void setBeanClass(Class<?> beanClass);

    boolean containsDepend(String name);
}
