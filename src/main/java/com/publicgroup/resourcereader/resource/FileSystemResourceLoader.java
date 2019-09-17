package com.publicgroup.resourcereader.resource;

import java.io.File;

public class FileSystemResourceLoader implements ResourceLoader  {

    @Override
    public Resource getResource(String path) {
        return new FileSystemResource(path);
    }

    //添加通过File对象获取Resource
    public Resource getResource(File file){
        return new FileSystemResource(file);
    }
}
