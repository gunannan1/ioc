package com.publicgroup.resourcereader.reader;


import com.publicgroup.factory.support.BeanDefinitionRegistry;
import com.publicgroup.resourcereader.resource.Resource;
import com.publicgroup.resourcereader.resource.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
	
	protected final BeanDefinitionRegistry registry;

	private ResourceLoader resourceLoader;
	
	public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
		this.registry=registry;
		this.resourceLoader = (ResourceLoader) this.registry;
	}

	@Override
	public BeanDefinitionRegistry getBeanDefinitionRegistry() {
		return this.registry;
	}

	@Override
	public ResourceLoader getResourceLoader() {
		return this.resourceLoader;
	}
		
}
