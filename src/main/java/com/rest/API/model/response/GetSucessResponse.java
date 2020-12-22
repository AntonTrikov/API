package com.rest.API.model.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetSucessResponse extends ResponseEntity {
    public GetSucessResponse(Object body, HttpStatus status) {
        super(body, status);
    }
}
