package com.publicgroup.resourcereader;

import com.publicgroup.resourcereader.resource.Resource;

public interface ResourceLoader {

    Resource getResource(String location);
}
