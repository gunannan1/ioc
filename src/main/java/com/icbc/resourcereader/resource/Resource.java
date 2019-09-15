package com.icbc.resourcereader.resource;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface Resource {

    InputStream getInputStream() throws IOException;

    boolean FileExists();

    File getFile() throws IOException;

    //return a  description for the  resource
    String getDescription();
}
