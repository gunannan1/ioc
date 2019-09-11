package com.spdb.Factory;


import com.spdb.Util.Log.LogFactory;

import java.util.logging.Logger;

 public abstract class AbstractFactoryBean<T>implements FactoryBean<T> {
     private static Logger log = LogFactory.getGlobalLog();
     private boolean singleton = true;
     private BeanFactory beanFactory;
     private boolean initialized = false;

     public  AbstractFactoryBean(){}

     public void setSingleton(boolean singleton) {
         this.singleton = singleton;
     }

     @Override
     public boolean isSingleton() {
         return this.singleton;
     }

     public void setBeanFactory(BeanFactory beanFactory) {
         this.beanFactory = beanFactory;
     }

     public BeanFactory getBeanFactory() {
         return beanFactory;
     }

     @Override
     public T getObject() throws Exception {
         return null;
     }
 }
