package com.icbc.factory;

/**
 * @author Yuxudong
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 拥有自动注入bean的功能，检查Map集合，如果有@Autowired注解，进行自动注入
     */
    void AutowireBean();
}
