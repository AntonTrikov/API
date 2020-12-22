package com.rest.API.model.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PutSuccessResponse extends ResponseEntity {
    public PutSuccessResponse(HttpStatus status) {
        super(status);
    }
}
