package com.publicgroup.config;

import java.util.Map;

/**
 * @author Kenny
 *本接口为beanDefinition提供存放解析bean中的基本属性能力
 */
public interface AttributeAccessor {

    /**
     * @param name 属性名
     * @param value 属性值
     * 设定属性
     */
    void setAttribute(String name,Object value);

    /**
     * @param name 属性名
     * @return 指定属性名的值，如果不存在应为null
     */
    Object getAttribute(String name);

    /**
     * @return 所有属性值
     * 获取所有属性值的缓存
     */
    Map<String, Object>getAttributes();

    /**
     * @param name 属性名
     * @return 是否存在属性
     */
    boolean containsAttribute(String name);

}
