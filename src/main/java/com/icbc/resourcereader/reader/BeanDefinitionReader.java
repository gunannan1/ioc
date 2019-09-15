package com.icbc.resourcereader.reader;


import com.icbc.factory.support.BeanDefinitionRegistry;
import com.icbc.resourcereader.DefaultResourceLoad;
import com.icbc.resourcereader.ResourceLoad;
import com.icbc.resourcereader.resource.Resource;

public interface BeanDefinitionReader {
	
	BeanDefinitionRegistry getBeanDefinitionRegistry();
	
	ResourceLoad getResourceLoader();
	
	int loadBeanDefinitions(Resource... resources) throws Exception;
	
	int loadBeanDefinitions(Resource resource) throws  Exception;
	
}
