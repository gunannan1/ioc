package com.publicgroup.resourcereader.reader;


import com.publicgroup.factory.support.BeanDefinitionRegistry;
import com.publicgroup.resourcereader.resource.Resource;
import com.publicgroup.resourcereader.resource.ResourceLoader;



public interface BeanDefinitionReader {
	
	BeanDefinitionRegistry getBeanDefinitionRegistry();
	
	ResourceLoader getResourceLoader();
	
	int loadBeanDefinitions(Resource... resources) throws Exception;
	
	int loadBeanDefinitions(Resource resource) throws  Exception;
	
}
