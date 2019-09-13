package com.icbc.config;

import java.util.List;

public interface BeanDefinition {

    //获得依赖BeanDefinition的名字
    List<String> getDepends();

    void addDepend(String depend);

    String getDescription();

    Class<?> getBeanClass();

    void setBeanClass(Class<?> beanClass);
}
