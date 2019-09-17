package com.publicgroup.resourcereader.reader;


import com.publicgroup.config.BeanDefinition;
import com.publicgroup.factory.support.BeanDefinitionRegistry;
import com.publicgroup.factory.support.XmlParser;
import com.publicgroup.resourcereader.resource.Resource;
import com.publicgroup.resourcereader.resource.XmlDocumentResource;
import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    //暂时保存beanDefinition，稍后在doLoadBeanDefinitions方法中注册到beanFactory
    protected Map<String, BeanDefinition> beanDefinitions = new HashMap<>();


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);

    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws Exception {
        return doLoadBeanDefinitions(resource);
    }

    public int doLoadBeanDefinitions(Resource resource) throws Exception {
        // 获取document并解析
        Document doc = doLoadDocument(resource);
        beanDefinitions = XmlParser.parse(doc);

        for (Entry<String, BeanDefinition> beanDefinition : beanDefinitions.entrySet()) {
            //注册beandefinition，可以理解为工厂通过registry获取beanDefinition
            // key is the name of bean,value is the beanDefinition
            registry.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
        }
        return beanDefinitions.size();
    }



    protected Document doLoadDocument(Resource resource) throws Exception {
        return new XmlDocumentResource(resource.getFile()).getDocument();
    }

	@Override
	public int loadBeanDefinitions(Resource... resources) throws Exception {
		// TODO Auto-generated method stub
		int count=0;
		for(Resource resource:resources) {
			count+=doLoadBeanDefinitions(resource);
		}
		return count;
	}


}
