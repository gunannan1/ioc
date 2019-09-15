package com.icbc;

import static org.junit.Assert.assertTrue;

import com.icbc.entity.Father;
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

        DefaultListableBeanFactory default1 = new DefaultListableBeanFactory(
                AppTest.class.getResource("/test.xml").getFile()
        );
        Father father= (Father) default1.getBean("father");
        System.out.println("father's name is "+father.getName());
    }
}
