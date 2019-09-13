package com.icbc.factory.support;
import com.icbc.util.log.LogFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import	java.util.logging.Logger;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final static Logger logger= LogFactory.getGlobalLog();

    private final Map SingletonObjects=new ConcurrentHashMap<String,Object>(64);

    @Override
    public void registerSingleton(String beanName, Object beanInstance) {
        SingletonObjects.put(beanName,beanInstance);
        logger.info("put bean:"+beanName + ""+beanInstance.toString());
    }

    @Override
    public Object getSingleton(String beanName) {
        return SingletonObjects.get(beanName);
    }

    @Override
    public boolean containSingleton(String beanName) {
        return SingletonObjects.containsKey(beanName);
    }

    @Override
    public int getSingleCount() {
        return SingletonObjects.size();
    }
}
