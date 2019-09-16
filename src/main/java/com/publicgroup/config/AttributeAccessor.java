package com.publicgroup.config;

import java.util.Map;

public interface AttributeAccessor {

    void setAttribute(String name,Object value);

    <T>void setAttribute(String name,Class clazz, T value);

    Object getAttribute(String name);

    Map<String, Object>getAttributes();

    Class<?> getType(String name);

    <T> T getAttributeByName(String name,Class clazz);

    boolean containsAttribute(String name);

    <T> T removeAttribute(String name);
}
