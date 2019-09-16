package com.publicgroup.resourcereader.resource;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlDocumentResource extends FileSystemResource{

    public XmlDocumentResource(File file)
    {
        super(file);
    }

    public XmlDocumentResource(String path)
    {
        super(path);
    }

    public Document getDocument() throws Exception{
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder db= dbFactory.newDocumentBuilder();
        Document doc=db.parse(getFile());
        return doc;
    }

}

