package com.publicgroup.resourcereader.resource;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface Resource {

    InputStream getInputStream() throws IOException;

    boolean FileExists();

    File getFile() throws IOException;

    //返回对resource的描述
    String getDescription();
}
