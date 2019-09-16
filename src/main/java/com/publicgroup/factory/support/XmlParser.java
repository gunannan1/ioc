package com.publicgroup.factory.support;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import	java.util.Map;

import com.publicgroup.config.BeanDefinition;
import com.publicgroup.config.DefaultBeanDefinition;
import com.publicgroup.util.Converter;
import com.publicgroup.util.log.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.logging.Logger;

public class XmlParser {

    public final static Logger logger= LogFactory.getGlobalLog();

    private static Map<String, BeanDefinition> beanDefinitions = new HashMap<>();
    private static List<String> ComponentPackageNames = new ArrayList<String>();

    //uncompleted
    public static final Map<String,BeanDefinition> parse(Document document) throws Exception{
        //获取根节点
        Element root=document.getDocumentElement();
        logger.info("根节点标记"+root.getTagName());
        //遍历节点获取package-scan
        NodeList packages=root.getElementsByTagName("package-scan");
        for (int i=0;i<packages.getLength();i++){
            Element node = (Element) packages.item(i);
            ComponentPackageNames.add(node.getAttribute("package-name"));
        }
        //遍历节点扫描bean
        NodeList nodeList=root.getElementsByTagName("bean");
        for (int i=0;i<nodeList.getLength();i++){

            Element node = (Element) nodeList.item(i);
            String id = node.getAttribute("name");
            String classPath = node.getAttribute("class");
            BeanDefinition beanDefinition = new DefaultBeanDefinition();
            beanDefinition.setBeanClass(Class.forName(classPath));


            NodeList properties = node.getElementsByTagName("property");
            for(int j=0;j<properties.getLength();j++){

                Element property=(Element) properties.item(j);

                String name = property.getAttribute("name");
                String value = property.getAttribute("value");
                String ref = property.getAttribute("ref");
                String type = property.getAttribute("type");
                if(value != null&&value.length()>0){
                    beanDefinition.setAttribute(name, Converter.cast2Class(type),value);
                }
                else if(ref != null&&ref.length()>0){
                    beanDefinition.addDepend(name,ref);
                }

            }
            beanDefinitions.put(id,beanDefinition);
        }
        return beanDefinitions;
    }

    public static BeanDefinition getBeanDefinition(String name) {
        return beanDefinitions.get(name);
    }

    // 获取配置文件中的包名
    public static List<String> getComponentPackageNames() {
        return ComponentPackageNames;
    }
}
