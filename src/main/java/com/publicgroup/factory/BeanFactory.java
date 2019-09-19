package com.publicgroup.factory;

import com.publicgroup.config.BeanDefinition;
import com.publicgroup.exception.BeanException;
import com.publicgroup.exception.NoSuchBeanDefinitionException;


/**
 * @author Kenny
 * Bean工厂
 */
public interface BeanFactory {

    /**
     * @param name bean的名字
     * @return  bean对象
     * @throws BeanException
     * 获取bean对象
     */
    Object getBean(String name) throws BeanException;

    /**
     * @param name bean的名字
     * @param requiredType 指定的类型
     * @param <T>
     * @return 类型为T的bean对象
     * @throws BeanException
     * 获取指定类的bean对象
     */
    <T> T getBean(String name,Class <T> requiredType) throws BeanException;

    /**
     * @param beanDefinitionName
     * @return 判断beanDefinition是否存在于缓存中
     */
    boolean containsBeanDefintion(String beanDefinitionName);

    /**
     * @param beanDefinitionName
     * @return 判断单例
     * @throws NoSuchBeanDefinitionException
     */
    boolean isSingleton(String beanDefinitionName) throws NoSuchBeanDefinitionException;

    /**
     * @param beanDefinitionName
     * @return BeanDefinition
     * 获取bean的定义对象
     */
    BeanDefinition getBeanDefinition(String beanDefinitionName);

}
