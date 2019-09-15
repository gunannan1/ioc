package com.icbc;

import static org.junit.Assert.assertTrue;

import com.icbc.config.BeanDefinition;
import com.icbc.context.AutowireApplicationContext;
import com.icbc.entity.Father;
import com.icbc.entity.Son;
import com.icbc.factory.DefaultListableBeanFactory;
import com.icbc.resourcereader.reader.XmlBeanDefinitionReader;
import org.junit.Assert;
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

        Father father= (Father) default1.getBean("father");
        Father father3= (Father) default1.getBean("father");

        Son son=(Son)default1.getBean("son");
//        System.out.println("father's name is "+father.getName());
        Assert.assertEquals(father.getName(),"老王");
        Assert.assertEquals(son.getName(),"小王");
        Assert.assertEquals(father,father3);

        Assert.assertEquals(son,father.getSon());

    }
}
