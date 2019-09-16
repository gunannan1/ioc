package com.icbc.exception;

public class AnnotationBenaConfigurationErrorException extends BeanException {
    public AnnotationBenaConfigurationErrorException(String msg) {
        super("注解配置异常："+msg);
    }
}
