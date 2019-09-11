package com.spdb.Factory;

import com.spdb.Exception.BeanException;
import com.spdb.Exception.NoSuchBeanDefinitionException;

public interface BeanFactory {
    Object getBean(String name)throws BeanException;

    <T> T getBean(String name,Class <T> requiredType)throws BeanException;

    boolean containsBeanDefintion(String beanDefinitionName)throws NoSuchBeanDefinitionException;

    boolean isSingleton(String beanDefinitionName);

}
