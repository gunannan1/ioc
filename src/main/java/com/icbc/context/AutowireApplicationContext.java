package com.icbc.context;

import com.icbc.factory.AutowireCapableBeanFactory;
import com.icbc.factory.DefaultListableBeanFactory;
import com.icbc.factory.support.BeanDefinitionRegistry;
import com.icbc.resourcereader.reader.AnnotationBeanDefinitionReader;
import com.icbc.resourcereader.resource.Resource;
import com.icbc.util.log.LogFactory;

import java.util.logging.Logger;

public class AutowireApplicationContext extends DefaultListableBeanFactory implements AutowireCapableBeanFactory {
    private static final Logger logger= LogFactory.getGlobalLog();


    public AutowireApplicationContext(Resource resource) throws Exception {
        super(resource);
        refresh();
    }
    public AutowireApplicationContext(String location) throws Exception {
        super(location);
        refresh();
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

    @Override
    public void AutowireBean() {

    }
}
