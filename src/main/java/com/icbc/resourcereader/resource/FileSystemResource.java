package com.icbc.resourcereader.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource extends AbstractResource {

    private final File file;

    private final String path;

    public FileSystemResource(File file){
        this.file = file;
        this.path = file.getAbsolutePath();
    }

    public FileSystemResource(String path){
        this.path = path;
        this.file = new File(path);
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return "资源的描述："+file.getName()+
                "  "+"地址为："+path;
    }

    @Override
    public File getFile(){
        return this.file;
    }
}
