package com.publicgroup;

import com.publicgroup.config.BeanDefinition;
import com.publicgroup.factory.support.XmlParser;
import com.publicgroup.resourcereader.resource.XmlDocumentResource;
import com.publicgroup.util.log.LogFactory;
import org.junit.Test;

import java.io.File;
import java.util.Map;
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
