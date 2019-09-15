package com.icbc.resourcereader;

import com.icbc.resourcereader.resource.FileSystemResource;
import com.icbc.resourcereader.resource.Resource;

public class DefaultResourceLoad implements ResourceLoad {
    @Override
    public Resource getResource(String location) {
        return new FileSystemResource(location);
    }
}
