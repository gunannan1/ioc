package com.icbc.config;
import	java.util.Map;
import com.icbc.util.log.LogFactory;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
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
    Map<String,String> dependentBeanDefinitions=new ConcurrentHashMap<>();

    Map<String,Object>attributes=new ConcurrentHashMap<>();
    Map <String,Class>attributesType=new ConcurrentHashMap<>();

    @Override
    public Map getDepends() {
        return dependentBeanDefinitions;
    }

    @Override
    public void addDepend(String name,String depend) {
        dependentBeanDefinitions.put(name,depend);
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

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name,value);
        attributesType.put(name,Object.class);
    }

    @Override
    public <T> void setAttribute(String name, Class clazz, T value) {
        attributes.put(name,value);
        attributesType.put(name,clazz);
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public <T> T getAttributeByName(String name, Class tClass) {
        if (attributesType.get(name)==tClass){
            return (T) attributes.get(name);
        }else{
            logger.severe("No such Type Value");
            return null;
        }
    }

    @Override
    public boolean containsAttribute(String name) {
        return attributes.containsKey(name);
    }

    @Override
    public <T> T removeAttribute(String name) {
        if (containsAttribute(name)){
            T value= (T) attributes.get(name);
            attributes.remove(name);
            attributesType.remove(name);
            return value;
        }else{
            logger.severe("Not Found "+name);
            return null;
        }
    }

    @Override
    public Map<String, Object>getAttributes(){
        return attributes;
    }
    @Override
    public Class<?> getType(String name){
        return attributesType.get(name);
    }
}
