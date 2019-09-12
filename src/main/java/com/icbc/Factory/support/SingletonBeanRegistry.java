package com.icbc.Factory.support;

public interface SingletonBeanRegistry{

    void registerSingleton(String beanName,Object beanInstance);

    Object getSingleton(String beanName);

    boolean containSingleton(String beanName);

    int getSingleCount();
}