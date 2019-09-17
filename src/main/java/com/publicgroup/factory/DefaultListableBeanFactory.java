package com.publicgroup.factory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import	java.util.logging.Logger;

import com.publicgroup.config.BeanDefinition;
import com.publicgroup.exception.CircularDependException;
import com.publicgroup.exception.NoSuchBeanDefinitionException;
import com.publicgroup.factory.support.BeanDefinitionRegistry;
import com.publicgroup.resourcereader.reader.XmlBeanDefinitionReader;
import com.publicgroup.resourcereader.resource.FileSystemResource;
import com.publicgroup.resourcereader.resource.Resource;
import com.publicgroup.resourcereader.resource.ResourceLoader;
import com.publicgroup.util.Converter;
import com.publicgroup.util.log.LogFactory;


public class DefaultListableBeanFactory extends AbstractBeanFactory implements
        BeanDefinitionRegistry,ResourceLoader {

    private static Logger logger = LogFactory.getGlobalLog();
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    protected Resource resource;

    public DefaultListableBeanFactory(Resource resource) throws Exception {
        this.resource = resource;
        refresh();
    }

    public DefaultListableBeanFactory(String location) throws Exception {
        this.resource = getResource(location);
        refresh();
    }

    // 好了，最后给定一个初始化方法
    protected void refresh() throws Exception {
        // 在这里，我们完成容器的初始化
        // 1.资源我们已经在构造方法中获取
        // 2.资源的解析
        int count = new ResourceReaderBeanFactory(this).loadBeanDefinitions(resource);
        // 3.容器的注册方法会被自动调用，此时注册就完成了
        logger.info("一共初注册了" + count + "个beanDefinition");
    }

    protected class ResourceReaderBeanFactory extends XmlBeanDefinitionReader {
        public ResourceReaderBeanFactory(BeanDefinitionRegistry registry) {
            super(registry);
        }
    }




    @Override
    protected Object createBean(String BeanName, BeanDefinition beanDefinition){
        // 如何通过beanDefinition获得一个完整的bean实例（我们已经获取了bean的依赖集合）
        // 当createBean的时候，它所依赖的bean一定已经创建完成了，并且已经放入了完成池
        // 反射获取方法，进行bean的注入
        List depends=beanDefinition.getDepends();
        Class<?> cl = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            // 通过反射生成bean的实例
            bean = cl.newInstance();
        } catch (Exception e1) {
            logger.log(Level.SEVERE,"反射异常");
        }
        /*遍历所有属性*/
        Field[]fields=cl.getDeclaredFields();
        for (Field field : fields){
            /*如果是基本属性注入*/
            if(beanDefinition.containsAttribute(field.getName())){
                Object value = beanDefinition.getAttribute(field.getName());
                bean=invokeAttributes(bean,field.getName(),value);
            }else if(beanDefinition.containsDepend(field.getName())){
            /*bean依赖注入*/
                if (creatingBeanPool.get(field.getName())!=null){
                    logger.severe("beanDefinition中存在循环依赖");
                    throw new CircularDependException("beanDefinition中存在循环依赖");
                }
                try {
                    invokeDepends(bean,field.getName(),completedBeanPool.get(field.getName()));
                } catch (NoSuchFieldException e) {
                    logger.log(Level.SEVERE,"context:",e);
                }
            }else{
                logger.severe(field.getName()+"is not found in BeanDefinition");
            }
        }

        registerSingleton(BeanName,bean);
        return bean;
    }

    private Object invokeDepends(Object bean,String Name, Object args) throws NoSuchFieldException {
        String methodName= "set" + Name.substring(0, 1).toUpperCase() + Name.substring(1);
        try {
            Method method = bean.getClass().getMethod(methodName, completedBeanPool.get(Name).getClass());
             /*调用set方法完成注入*/
            method.invoke(bean, completedBeanPool.get(Name));
        } catch (NoSuchMethodException e) {
            logger.severe("需要获取得bean中没有" + methodName + "方法");
        } catch (Exception e) {
            logger.severe("获取到了set方法，没有能获取到需要注入的bean:" +args);
        }
        return bean;
    }


    private Object invokeAttributes(Object bean, String Name,  Object args) {
        String methodName = "set" + Name.substring(0, 1).toUpperCase() + Name.substring(1);
        try {

            Field field =bean.getClass().getDeclaredField(Name);
            Method method = bean.getClass().getMethod(methodName, field.getType());
            method.invoke(bean, field.getType().cast(Converter.Object2Value(field.getType(), args)));
        } catch (Exception e) {
            logger.severe("基本类型注入时方法调用错误，可能原因：属性名配置错误，类型不匹配\n"+
                    "方法名："+methodName+"参数："+args);
        }
        return bean;
    }


    @Override
    public boolean containsBeanDefintion(String beanDefinitionName) {
        return beanDefinitionMap.containsKey(beanDefinitionName);
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
