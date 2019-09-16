package com.publicgroup.context;
import	java.lang.reflect.Field;

import com.publicgroup.annotation.Autowired;

import com.publicgroup.factory.AutowireCapableBeanFactory;
import com.publicgroup.factory.DefaultListableBeanFactory;
import com.publicgroup.factory.support.BeanDefinitionRegistry;
import com.publicgroup.resourcereader.reader.AnnotationBeanDefinitionReader;
import com.publicgroup.resourcereader.resource.Resource;
import com.publicgroup.util.log.LogFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AutowireApplicationContext extends DefaultListableBeanFactory implements AutowireCapableBeanFactory {
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


    @Override
    public Object AutowireBean(String BeanName) throws IllegalAccessException, InstantiationException {

            Object Bean = getSingleton(BeanName);
            if(Bean==null) {
                Class<?> BeanClass = getBeanDefinition(BeanName).getBeanClass();
                Bean=BeanClass.newInstance();
                Field[] fields = BeanClass.getFields();
                if (fields.length > 0) {
                    for (Field f : fields) {
                        Autowired autowired = f.getAnnotation(Autowired.class);
                        if (autowired != null) {
                            try {
                                f.setAccessible(true);
                                if (f.get(Bean) != null) {
                                    if (!autowired.value().equals("")) {
                                        f.set(Bean, autowired.value());
                                    } else {
                                        Object ref = getBean(f.getName());
                                        f.set(Bean, ref);
                                    }
                                }
                            } catch (IllegalAccessException e) {
                                logger.log(Level.SEVERE,"Error:",e);
                            }

                        }
                    }
                }
                registerSingleton(BeanName,Bean);
            }
        return Bean;
    }
}
