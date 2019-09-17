package com.publicgroup.context;
import	java.lang.reflect.Field;

import com.publicgroup.annotation.Autowired;

import com.publicgroup.factory.DefaultListableBeanFactory;
import com.publicgroup.factory.support.BeanDefinitionRegistry;
import com.publicgroup.resourcereader.reader.AnnotationBeanDefinitionReader;
import com.publicgroup.resourcereader.resource.Resource;
import com.publicgroup.util.log.LogFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AutowireApplicationContext extends DefaultListableBeanFactory  {
    private static final Logger logger= LogFactory.getGlobalLog();


    public AutowireApplicationContext(Resource resource) throws Exception {
        super(resource);
    }
    public AutowireApplicationContext(String location) throws Exception {
        super(location);
    }

    private class AutowireAnnotationBeanDefinition extends AnnotationBeanDefinitionReader {
        public AutowireAnnotationBeanDefinition(BeanDefinitionRegistry registry)
        {
            super(registry);
        }
    }

    @Override
    protected void refresh() throws Exception {
        int count=new AutowireAnnotationBeanDefinition(this).loadBeanDefinitions(resource);
        logger.info("一共初注册了:"+count+"个beanDefinition");
    }

}
