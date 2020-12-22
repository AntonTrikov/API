package com.rest.API.model.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DeleteSuccessResponse extends ResponseEntity {
    public DeleteSuccessResponse(HttpStatus status) {
        super(status);
    }
}
