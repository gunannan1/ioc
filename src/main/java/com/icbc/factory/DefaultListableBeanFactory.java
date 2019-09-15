package com.icbc.factory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import	java.util.logging.Logger;

import com.icbc.config.BeanDefinition;
import com.icbc.exception.BeanException;
import com.icbc.exception.NoSuchBeanDefinitionException;
import com.icbc.util.log.LogFactory;


public class DefaultListableBeanFactory extends AbstractBeanFactory implements
        ListableBeanFactory {

    private static Logger logger = LogFactory.getGlobalLog();
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(256);


    @Override
    public boolean containsBeanDefinition(String var1) {
        return false;
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public Object getBean(String name) throws BeanException {
        return null;
    }

    @Override
    public Object getBean(String name, Class requiredType) throws BeanException {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public Class getType(String name) throws NoSuchBeanDefinitionException {
        return null;
    }
}
