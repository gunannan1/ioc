package com.publicgroup.factory.support;
import com.publicgroup.util.log.LogFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import	java.util.logging.Logger;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private  static final Logger logger= LogFactory.getGlobalLog();

    private final Map singletonObjects=new ConcurrentHashMap<String,Object>(64);

    @Override
    public void registerSingleton(String beanName, Object beanInstance) {
        singletonObjects.put(beanName,beanInstance);
        logger.info("put bean:"+beanName + "  "+beanInstance.toString());
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public Map<String,Object> getSingletonObjects(){return singletonObjects;}

    @Override
    public boolean containSingleton(String beanName) {
        return singletonObjects.containsKey(beanName);
    }

    @Override
    public int getSingleCount() {
        return singletonObjects.size();
    }
}
