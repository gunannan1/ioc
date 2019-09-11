package com.icbc.Factory;

import com.icbc.Exception.BeanException;
import com.icbc.Exception.NoSuchBeanDefinitionException;

/**
 *
 */
public interface BeanFactory {

    /**
     * 对FactoryBean的转义定义，因为如果使用bean的名字检索FactoryBean得到的对象是工厂生成的对象，
        如果需要得到工厂本身，需要转义
     */
    String FACTORY_BEAN_PREFIX = "&";

    /**
     * @param name
     * @return
     * @throws BeanException
     * 根据bean的名字，获取在IOC容器中得到bean实例
     */
    Object getBean(String name) throws BeanException;

    /**
     * @param name
     * @param requiredType
     * @return
     * @throws BeanException
     *  //根据bean的名字和Class类型来得到bean实例，增加了类型安全验证机制。
     */
    Object getBean(String name, Class requiredType) throws BeanException;

    /**
     * @param name
     * @return
     * //提供对bean的检索，看看是否在IOC容器有这个名字的bean
     */
    boolean containsBean(String name);

    /**
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     * //根据bean名字得到bean实例，并同时判断这个bean是不是单例
     */

    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;


    /**
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     * //得到bean实例的Class类型
     */
    Class getType(String name) throws NoSuchBeanDefinitionException;


}
