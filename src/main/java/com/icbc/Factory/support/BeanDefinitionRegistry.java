package com.icbc.Factory.support;

import com.icbc.Config.BeanDefinition;

public interface BeanDefinitionRegistry {


    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    void removeBeanDefinition(String beanName);
}
