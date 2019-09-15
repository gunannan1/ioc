package com.icbc.resourcereader.reader;


import com.icbc.factory.support.BeanDefinitionRegistry;
import com.icbc.resourcereader.DefaultResourceLoader;
import com.icbc.resourcereader.ResourceLoader;
import com.icbc.resourcereader.resource.Resource;

public interface BeanDefinitionReader {
	
	BeanDefinitionRegistry getBeanDefinitionRegistry();
	
	ResourceLoader getResourceLoader();
	
	int loadBeanDefinitions(Resource... resources) throws Exception;
	
	int loadBeanDefinitions(Resource resource) throws  Exception;
	
}
