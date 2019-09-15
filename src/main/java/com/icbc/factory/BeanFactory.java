package com.icbc.factory;

import com.icbc.config.BeanDefinition;
import com.icbc.exception.BeanException;
import com.icbc.exception.NoSuchBeanDefinitionException;

/**
 *
 */
public interface BeanFactory {

    Object getBean(String name) throws BeanException;

    <T> T getBean(String name,Class <T> requiredType) throws BeanException;

    boolean containsBeanDefintion(String beanDefinitionName);

    boolean isSingleton(String beanDefinitionName) throws NoSuchBeanDefinitionException;

    BeanDefinition getBeanDefinition(String beanDefinitionName);

}
