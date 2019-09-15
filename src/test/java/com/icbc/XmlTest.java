package com.icbc;

import com.icbc.config.BeanDefinition;
import com.icbc.entity.Father;
import com.icbc.factory.support.XmlParser;
import com.icbc.util.log.LogFactory;
import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class XmlTest {

    // 自定义的全局log（个人一般用这个记录）
    private static Logger log = LogFactory.getGlobalLog();
    // Jdk1.7以后自带的全局log（后面我添加了FileHandler，用于写入文件日志）
    private static Logger sysLog = Logger.getGlobal();

    @Test
    public void test() throws Exception {
        Map<String,Object> map= XmlParser.parse(XmlParser.getDocument(XmlTest.class.getClassLoader().getResource("test.xml").getFile()));
        BeanDefinition father= (BeanDefinition) map.get("father");
        for (String value:father.getDepends()){
            System.out.println(value);
        }
    }
}
