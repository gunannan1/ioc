package com.icbc.factory;
import java.util.HashMap;
import java.util.Map;
import	java.util.logging.Logger;

import com.icbc.exception.BeanException;
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
        //开始创建bean,记录在新生池中
        creatingBeanPool.put(name,requiredType);

        Object bean;
        //检查是否已经有过当前class的单例
        if ((bean = getSingleton(name)) != null){
            //如果没有要求requiretype，或者requiretype与单例池中类型不符合
            if (requiredType==null||!requiredType.isInstance(bean)){
                logger.warning("传Bean入的requiredType==null  ||  不是所要求类型的实例");
                throw new BeanException("类型转换错误");
            }
        }

        return null;
    }
}
