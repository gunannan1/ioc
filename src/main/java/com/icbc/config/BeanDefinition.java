package com.icbc.config;

import java.util.List;
import java.util.Map;


public interface BeanDefinition extends AttributeAccessor{

    //获得依赖BeanDefinition的名字
    Map getDepends();

    void addDepend(String name,String depend);

    String getDescription();

    Class<?> getBeanClass();

    void setBeanClass(Class<?> beanClass);
}
