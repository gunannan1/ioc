package com.icbc.exception;


public class NoSuchBeanDefinitionException extends Exception {

    /**  */
    private static final long serialVersionUID = 5439915454935047935L;

    public NoSuchBeanDefinitionException(String msg)
    {
        //TODO日志输出
        super(msg);
    }

}
