package com.icbc;

import com.icbc.config.BeanDefinition;
import com.icbc.entity.Father;
import com.icbc.factory.support.BeanDefinitionRegistry;
import com.icbc.factory.support.XmlParser;
import com.icbc.resourcereader.reader.XmlBeanDefinitionReader;
import com.icbc.resourcereader.resource.Resource;
import com.icbc.resourcereader.resource.XmlDocumentResource;
import com.icbc.util.log.LogFactory;
import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class XmlTest {

    // 自定义的全局log（个人一般用这个记录）
    private static Logger log = LogFactory.getGlobalLog();


    @Test
    public void test() throws Exception {

        XmlDocumentResource resource = new XmlDocumentResource(new File(XmlTest.class.getResource("/test.xml").getFile()));
        Map<String,BeanDefinition> map=XmlParser.parse(resource.getDocument());

        for (Map.Entry<String, BeanDefinition> beanDefinition : map.entrySet()){
            System.out.println(beanDefinition.getKey());
            System.out.println(beanDefinition.getValue().getDepends().toString());
        }
        for (String s:XmlParser.getComponentPackageNames()){
            System.out.println(s);
        }
    }
}
