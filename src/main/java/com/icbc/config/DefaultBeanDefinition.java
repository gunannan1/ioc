package com.icbc.config;

public class DefaultBeanDefinition extends AbstractBeanDefinition {

    @Override
    public String getDescription() {
        return getBeanClass().getName();
    }
}
