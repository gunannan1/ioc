package com.publicgroup.resourcereader;

import com.publicgroup.resourcereader.resource.FileSystemResource;
import com.publicgroup.resourcereader.resource.Resource;

import java.io.File;

public class FileSystemResourceLoader extends DefaultResourceLoader {

    @Override
    public Resource getResource(String path) {
        return new FileSystemResource(path);
    }

    //添加通过File对象获取Resource
    public Resource getResource(File file){
        return new FileSystemResource(file);
    }
}
