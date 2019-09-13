package com.icbc.factory.support;

import com.icbc.config.BeanDefinition;

public interface BeanDefinitionRegistry {


    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    void removeBeanDefinition(String beanName);
}
