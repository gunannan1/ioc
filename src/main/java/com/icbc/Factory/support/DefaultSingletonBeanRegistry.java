package com.icbc.Factory.support;
import	java.util.logging.Logger;

import java.util.logging.Logger;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final static Logger logger=Logger.getGlobal();

    @Override
    public void registerSingleton(String beanName, Object beanInstance) {

    }

    @Override
    public Object getSingleton(String beanName) {
        return null;
    }

    @Override
    public boolean containSingleton(String beanName) {
        return false;
    }

    @Override
    public int getSingleCount() {
        return 0;
    }
}
