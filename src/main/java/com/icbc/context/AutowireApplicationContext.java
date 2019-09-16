package com.icbc.context;
import	java.lang.reflect.Field;

import com.icbc.annotation.Autowired;

import com.icbc.factory.AutowireCapableBeanFactory;
import com.icbc.factory.DefaultListableBeanFactory;
import com.icbc.factory.support.BeanDefinitionRegistry;
import com.icbc.resourcereader.reader.AnnotationBeanDefinitionReader;
import com.icbc.resourcereader.resource.Resource;
import com.icbc.util.log.LogFactory;

import java.util.Map;
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
    /*TODO*/
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
                                e.printStackTrace();
                            }

                        }
                    }
                }
                registerSingleton(BeanName,Bean);
            }
        return Bean;
    }
}
