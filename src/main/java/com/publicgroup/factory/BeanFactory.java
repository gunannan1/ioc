package com.publicgroup.factory;

import com.publicgroup.config.BeanDefinition;
import com.publicgroup.exception.BeanException;
import com.publicgroup.exception.NoSuchBeanDefinitionException;

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
