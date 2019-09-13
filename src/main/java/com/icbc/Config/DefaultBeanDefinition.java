package com.icbc.Config;

public class DefaultBeanDefinition extends AbstractBeanDefinition {

    @Override
    public String getDescription() {
        return getBeanClass().getName();
    }
}
