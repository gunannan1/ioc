package com.publicgroup.config;

import java.util.Map;

public interface AttributeAccessor {

    void setAttribute(String name,Object value);

    Object getAttribute(String name);

    Map<String, Object>getAttributes();

    boolean containsAttribute(String name);

    <T> T removeAttribute(String name);
}
