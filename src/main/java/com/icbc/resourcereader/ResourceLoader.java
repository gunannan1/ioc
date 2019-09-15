package com.icbc.resourcereader;

import com.icbc.resourcereader.resource.Resource;

public interface ResourceLoader {

    Resource getResource(String location);
}
