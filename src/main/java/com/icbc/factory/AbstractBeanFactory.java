package com.icbc.factory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import	java.util.logging.Logger;

import com.icbc.config.BeanDefinition;
import com.icbc.exception.BeanException;
import com.icbc.exception.CircularDependException;
import com.icbc.exception.NoSuchBeanDefinitionException;
import com.icbc.factory.support.DefaultSingletonBeanRegistry;
import com.icbc.util.log.LogFactory;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory{

    private final static Logger logger= LogFactory.getGlobalLog();

    /**
     *  创建完成bean池，我会将创建已完成的bean放入其中
     */
    protected Map completedBeanPool = new HashMap<String, Object>();
    /**
     *  创建bean新生池，将正在创建的bean放入其中
     */
    protected Map creatingBeanPool = new HashMap<String, Object>();


    @Override
    public Object getBean(String name) throws BeanException {
        return doGetBean(name, Object.class);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return doGetBean(name,requiredType);
    }

    /**
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeanException
     */
    protected <T> T doGetBean(String name, Class<T> requiredType) throws BeanException {
        // 将当前需要创建的bean放入新生池
        creatingBeanPool.put(name, requiredType);
        Object bean;
        // 首先我们急切的去单例池里面去找,如果单例池里面有
        if ((bean = getSingleton(name)) != null) {
            // 如果传入的requiredType==null || 不是所要求类型的实例
            if (requiredType == null || !requiredType.isInstance(bean)) {
                logger.severe("传入的requiredType==null  ||  不是所要求类型的实例");
                throw new ClassCastException("类型转换错误");
            }
        } else {
            // 获得beanDefinition
            BeanDefinition beanDefinition = getBeanDefinition(name);
            if (beanDefinition == null){
                try {
                    throw new NoSuchBeanDefinitionException("");
                } catch (NoSuchBeanDefinitionException e1) {
                    logger.severe("bean不存在:"+name);
                    return null;
                }
            }
            /*
             * 在创建bean之前，我先得将它依赖的bean进行创建 1.获取beanDefintionName
             */
            List<String> depends = beanDefinition.getDepends();
            // 如果当前创建的bean是依赖于其他bean的
            if (depends != null && depends.size() >= 1) {
                for (String depend : depends) {
                    //如果依赖的是基本类型，则不需要先进行创建
                    if(depend.indexOf('.')==0){
                        continue;
                    }
                    // 如果发现该bean的某些依赖不存在
                    if (!containsBeanDefintion(depend)) {
                        logger.warning("beanName: "+name + "     message:may be you will create  a  "
                                + "incomplete bean,依赖的bean:"+depend+"不存在！");
                        // 直接跳过，进入下一次循环
                        continue;
                    } else {
                        // 存在该bean依赖的beanDefinition，我们必须先创建它所依赖的bean
                        // 在这里，如果发现需要的依赖bean并没有创建完毕
                        if (creatingBeanPool.get(depend) != null) {
                            logger.severe("beanDefinition中存在循环依赖，请检查您配置文件！");
                            throw new CircularDependException("beanDefinition中存在循环依赖");
                        }
                        getBean(depend);
                    }
                }
            }
            // 此时bean已经创建完毕
            bean = createBean(name, beanDefinition);
            // 放入完成池,并将它移除新生池
            addToCompletedBeanPoolAndRemoveFromBabyBeanPool(name, bean);
        }
        return (T) bean;
    }

    // 将创建完成的bean放入完成池,并将它移除新生池
    private synchronized void addToCompletedBeanPoolAndRemoveFromBabyBeanPool(String name, Object bean) {
        if (completedBeanPool.get(name) == null) {
            completedBeanPool.put(name, bean);
        }
        creatingBeanPool.remove(name);
    }

    protected abstract Object createBean(String BeanName, BeanDefinition beanDefinition) throws CircularDependException;
}
