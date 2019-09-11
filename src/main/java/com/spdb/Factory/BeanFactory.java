package com.spdb.Factory;

public interface BeanFactory {
    Object getBean(String name);

    <T> T getBean(String name,Class <T> requiredType);

    boolean containsBeanDefintion(String beanDefinitionName);

    boolean isSingleton(String beanDefinitionName);

}
