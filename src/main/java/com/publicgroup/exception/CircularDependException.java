package com.publicgroup.exception;

public class CircularDependException extends BeanException {
    /**  */
    private static final long serialVersionUID = 1L;

    public CircularDependException(String msg) {
        super(msg);
    }
}
