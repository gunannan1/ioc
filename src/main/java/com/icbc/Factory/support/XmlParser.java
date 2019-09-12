package com.icbc.Factory.support;
import java.io.File;
import java.io.IOException;
import	java.util.Map;

import com.icbc.Util.Log.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.logging.Logger;

public class XmlParser {

    public final static Logger logger= LogFactory.getGlobalLog();

    private static DocumentBuilderFactory dbFactory = null;
    private static DocumentBuilder db = null;

    static {
        try {
            //创造解析工厂
            DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
            //指定DocumentBuilder
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            logger.warning("获取xml工厂失败");
            e.printStackTrace();
        }
    }


    private XmlParser(){}

    public static final Document getDocument(String url){
        if(dbFactory == null||db==null){
            try {
                //创造解析工厂
                dbFactory=DocumentBuilderFactory.newInstance();
                //指定DocumentBuilder
                db = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                logger.warning("获取xml工厂失败");
                e.printStackTrace();
            }

        }
        Document doc=null;
        try {
            doc=db.parse(new File(url));
        } catch (SAXException e) {
            logger.warning("文档构建失败");
            e.printStackTrace();
        } catch (IOException e) {
            logger.warning("文件读取失败");
            e.printStackTrace();
        }
        return doc;
    }

    //uncompleted
    public static final Map<String,Object> parse(Document document){
        //获取根节点
        Element root=document.getDocumentElement();
        logger.info("根节点标记"+root.getTagName());

        //遍历节点
        NodeList nodeList=root.getElementsByTagName("bean");
        for (int i=0;i<nodeList.getLength();i++){
            Element node = (Element) nodeList.item(i);

        }
        return null;
    }
}
