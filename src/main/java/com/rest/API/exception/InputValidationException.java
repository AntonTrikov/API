package com.rest.API.exception;

import com.rest.API.model.ErrorHandledEntity;

public class InputValidationException extends RuntimeException {
    public InputValidationException(String key, String message){
        super(InputValidationException.class.getName()+ ": "
                + key + "" );
    }
}
