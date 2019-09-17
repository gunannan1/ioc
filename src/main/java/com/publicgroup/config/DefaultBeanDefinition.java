package com.publicgroup.config;
import	java.util.Map;
import com.publicgroup.util.log.LogFactory;

import java.util.concurrent.ConcurrentHashMap;
import	java.util.logging.Logger;

/**
 * @author Yuxudong
 */
public class DefaultBeanDefinition implements BeanDefinition{

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


    @Override
    public Map getDepends() {
        return dependentBeanDefinitions;
    }

    @Override
    public void addDepend(String name,String depend) {
        dependentBeanDefinitions.put(name,depend);
    }


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
    public boolean containsDepend(String name) {
        return dependentBeanDefinitions.containsKey(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name,value);
    }


    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
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
	public String getDescription() {
		// TODO Auto-generated method stub
		return getClass().getName();
	}
}
