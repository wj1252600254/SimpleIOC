package com.sjtu.exception;

public class NoSuchBeanDefinitionException extends Exception {
    public NoSuchBeanDefinitionException() {
        super();
    }

    public NoSuchBeanDefinitionException(String message) {
        super(message);
    }
}
