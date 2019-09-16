package com.publicgroup.resourcereader.resource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlDocumentResource extends FileSystemResource{

    public XmlDocumentResource(File file)
    {
        super(file);
    }

    public XmlDocumentResource(String path)
    {
        super(path);
    }

    public Document getDocument() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder db= dbFactory.newDocumentBuilder();

        return db.parse(getFile());
    }

}

