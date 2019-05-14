package com.sjtu.exception;

public class BeanNameDuplicateException extends Exception {
    public BeanNameDuplicateException() {
        super();
    }

    public BeanNameDuplicateException(String message) {
        super(message);
    }
}
