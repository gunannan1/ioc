package com.icbc;

import static org.junit.Assert.assertTrue;

import com.icbc.config.BeanDefinition;
import com.icbc.context.AutowireApplicationContext;
import com.icbc.entity.Father;
import com.icbc.entity.Son;
import com.icbc.factory.DefaultListableBeanFactory;
import com.icbc.resourcereader.reader.XmlBeanDefinitionReader;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {

        AutowireApplicationContext default1 = new AutowireApplicationContext(
                AppTest.class.getResource("/test.xml").getFile()
        );
        BeanDefinition father=default1.getBeanDefinition("father");
        BeanDefinition son=default1.getBeanDefinition("son");


        Father father1 = (Father) default1.getBean("father");
        Son son1= (Son) default1.getBean("son");

        System.out.println(father1.toString());
    }
}
