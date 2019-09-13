package com.icbc.Config;
import com.icbc.Util.Log.LogFactory;

import java.util.ArrayList;
import	java.util.logging.Logger;

import java.util.List;

/**
 * @author Yuxudong
 */
public abstract class AbstractBeanDefinition implements BeanDefinition{

    public static final Logger logger = LogFactory.getGlobalLog();

    /**
     * 存储当前beandefinition的Class对象实例
     */
    private Object beanClass;

    /**
     * 当前bean所依赖的bean列表
     * spring中beanDefinition只返回bean的name
     */
    List<String> dependentBeanDefinitions=new ArrayList<>();

    @Override
    public List<String> getDepends() {
        return dependentBeanDefinitions;
    }

    @Override
    public void addDepend(String depend) {
        dependentBeanDefinitions.add(depend);
    }

    @Override
    public abstract String getDescription();

    @Override
    public Class<?> getBeanClass() {
        Object beanClassObject = this.beanClass;
        if (beanClassObject == null) {
            throw new IllegalStateException("No bean class specified on bean definition");
        }
        if (!(beanClassObject instanceof Class)) {
            throw new IllegalStateException(
                    "Bean class name [" + beanClassObject + "] has not been resolved into an actual Class");
        }
        return (Class<?>) beanClassObject;
    }

    @Override
    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
