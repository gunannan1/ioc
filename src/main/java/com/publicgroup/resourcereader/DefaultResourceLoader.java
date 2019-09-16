package com.publicgroup.resourcereader;

import com.publicgroup.resourcereader.resource.FileSystemResource;
import com.publicgroup.resourcereader.resource.Resource;

public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        return new FileSystemResource(location);
    }
}
