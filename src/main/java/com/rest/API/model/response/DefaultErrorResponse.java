package com.rest.API.model.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class DefaultErrorResponse extends ResponseEntity {

    public DefaultErrorResponse(HttpStatus status, List<String> errors) {
        super(createBody(status,errors), status);
    }

    public DefaultErrorResponse(HttpStatus status, String error) {
        super(createBody(status,error), status);
    }

    public static Map<String, Object> createBody(HttpStatus status, List<String> errors){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        responseBody.put("errors", errors);
        return responseBody;
    }

    public static Map<String, Object> createBody(HttpStatus status, String error){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        responseBody.put("errors", Arrays.asList(error));
        return responseBody;
    }

}
