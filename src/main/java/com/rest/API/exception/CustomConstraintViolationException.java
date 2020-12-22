package com.rest.API.exception;

public class CustomConstraintViolationException extends RuntimeException {
    public CustomConstraintViolationException(String error){
        super(CustomConstraintViolationException.class.getSimpleName()+": "+error);
    }
}
