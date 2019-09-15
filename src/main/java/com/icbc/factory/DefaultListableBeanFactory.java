package com.icbc.factory;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import	java.util.logging.Logger;

import com.icbc.config.BeanDefinition;
import com.icbc.exception.BeanException;
import com.icbc.exception.CircularDependException;
import com.icbc.exception.NoSuchBeanDefinitionException;
import com.icbc.factory.support.BeanDefinitionRegistry;
import com.icbc.resourcereader.ResourceLoader;
import com.icbc.resourcereader.resource.FileSystemResource;
import com.icbc.resourcereader.resource.Resource;
import com.icbc.util.log.LogFactory;


public class DefaultListableBeanFactory extends AbstractBeanFactory implements
        BeanDefinitionRegistry,ListableBeanFactory,ResourceLoader {

    private static Logger logger = LogFactory.getGlobalLog();
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(256);
    protected Resource resource;


    @Override
    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }


    @Override
    public Object getBean(String name) throws BeanException {
        return super.getBean(name);
    }

    @Override
    protected Object createBean(String BeanName, BeanDefinition beanDefinition) throws CircularDependException {
        // 如何通过beanDefinition获得一个完整的bean实例（我们已经获取了bean的依赖集合）
        // 当createBean的时候，它所依赖的bean一定已经创建完成了，并且已经放入了完成池
        // 反射获取方法，进行bean的注入
        List<String> depends = beanDefinition.getDepends();
        Class<?> cl = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            // 通过反射生成bean的实例
            bean = cl.newInstance();
        } catch (Exception e1) {
            logger.severe("反射异常");
        }
        if (depends != null && depends.size() >= 1) {
            // 此时的bean还不完整，还没有注入它的依赖，我们将它放入新生池
            creatingBeanPool.put(BeanName, bean);
            for (String depend : depends) {
                if (creatingBeanPool.get(depend) != null) {
                    logger.severe("beanDefinition中存在循环依赖");
                    throw new CircularDependException("beanDefinition中存在循环依赖");
                }
                // 在这里分两种情况，先确定需要注入的是基本类型还是bean
                if (isBaiscType(depend)) {
                    // 注入基本属性
                    try {
                        bean = beanBasicTypeAutowired(bean, depend);
                    } catch (Exception e) {
                        // 不做任何处理
                    }
                } else {
                    String methodName = "set" + depend.substring(0, 1).toUpperCase() + depend.substring(1);
                    // 获取bean的class对象
                    // 通过反射获取方法
                    try {
                        Method method = cl.getMethod(methodName, completedBeanPool.get(depend).getClass());
                        // 调用set方法完成注入
                        method.invoke(bean, completedBeanPool.get(depend));
                    } catch (NoSuchMethodException e) {
                        logger.severe("需要获取得bean中没有" + methodName + "方法");
                    } catch (Exception e) {
                        logger.severe("获取到了set方法，没有能获取到需要注入的bean:" + depend);
                    }
                }

            }
        }
        return bean;
    }

    /*TODO*/
    private Object beanBasicTypeAutowired(Object bean, String depend){
        return null;
    }

    private boolean isBaiscType(String depend) {
        return depend.charAt(0) == '.';
    }

    @Override
    public boolean containsBeanDefintion(String beanDefinitionName) {
        return beanDefinitionMap.containsKey(beanDefinitionName);
    }


    @Override
    public Object getBean(String name, Class requiredType) throws BeanException {
        return super.getBean(name,requiredType);
    }


    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return true;
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanDefinitionName) {
        return beanDefinitionMap.get(beanDefinitionName);
    }


    @Override
    public Resource getResource(String location) {
        return new FileSystemResource(location);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        if (beanDefinitionMap.get(beanName) != null) {
            beanDefinitionMap.remove(beanName);
        } else {
            logger.severe("需要移除的bean不存在！");
        }
    }
}
