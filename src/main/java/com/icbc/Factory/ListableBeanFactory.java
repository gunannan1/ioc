package com.icbc.Factory;

public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String var1);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();


}
