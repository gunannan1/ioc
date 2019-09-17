package com.publicgroup.resourcereader.reader;


import com.publicgroup.annotation.Autowired;
import com.publicgroup.annotation.component;
import com.publicgroup.config.BeanDefinition;
import com.publicgroup.config.DefaultBeanDefinition;
import com.publicgroup.factory.support.BeanDefinitionRegistry;
import com.publicgroup.factory.support.XmlParser;
import com.publicgroup.resourcereader.resource.Resource;
import com.publicgroup.util.Assert;
import com.publicgroup.util.Converter;
import com.publicgroup.util.ScanPackageUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/*
 * @description  直接继承XmlBeanDefinitionReader类实现，不仅能读取xml配置，
 * 还能自动将注解类注入IOC容器
 * 它从注解中获取beanDefinition
 */
public class AnnotationBeanDefinitionReader extends XmlBeanDefinitionReader {

    public AnnotationBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
}

    @Override
    public int loadBeanDefinitions(Resource resource) throws Exception {
        return doLoadBeanDefinitionsFromAnnotation(resource);
    }

    // 扫描所有的包
    public int loadBeanDefinitions() throws Exception {
        return doLoadBeanDefinitions(null);
    }

    // 通过注解生成beanDefinition
    public int doLoadBeanDefinitionsFromAnnotation(Resource resource) throws Exception {
        // 加载xml中定义的beanDefinition
        int count = super.doLoadBeanDefinitions(resource);
        // 获得包名，将包下的类进行解析
        List<String> packageNames = XmlParser.getComponentPackageNames();
        // 读取
        if (Assert.isNotEmpty(packageNames)) {
            for (String PackageName : packageNames) {
                // 获得包下的所有类名
                List<String> classNames = ScanPackageUtil.getClassName(PackageName);
                if (Assert.isNotEmpty(classNames)) {
                    for (String ClassName : classNames) {
                        BeanDefinition beanDefinition = new DefaultBeanDefinition();
                        // 获得beanDefinition的beanClass
                        Class<?> beanClass = Class.forName(ClassName);
                        // 验证是否有Component注解
                        component com = beanClass.getAnnotation(component.class);
                        if (com != null) {
                            beanDefinition.setBeanClass(beanClass);
                            // 还要获取它的依赖
                            Field[] fields = beanClass.getDeclaredFields();
                            if (fields.length > 0) {
                                for (Field f : fields) {
                                    Autowired autowired = f.getAnnotation(Autowired.class);
                                    if (autowired != null) {

                                        if(!autowired.value().equals("")){
                                            beanDefinition.setAttribute(f.getName(),f.getType().cast(Converter.Object2Value(f.getType(),autowired.value())));
                                        }else{
                                            beanDefinition.addDepend(f.getName());
                                        }

                                    }
                                }
                            }
                            // 默认使用全部小写的方式
                            String beanDefinitionName =
                                    (ClassName.substring(ClassName.lastIndexOf('.') + 1)).toLowerCase();
                            beanDefinitions.put(beanDefinitionName, beanDefinition);
                            count++;
                        }
                    }
                }
            }
        }
        for (Map.Entry<String, BeanDefinition> beanDefinition : beanDefinitions.entrySet()) {
            //注册beandefinition，可以理解为工厂通过resource获取beanDefinition
            registry.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
        }
        return count;
    }
}
