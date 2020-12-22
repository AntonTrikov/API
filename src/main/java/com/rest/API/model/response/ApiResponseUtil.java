package com.rest.API.model.response;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ApiResponseUtil {
    public static URI createLocation(int id, ServletUriComponentsBuilder uriBuilder){
        return uriBuilder.path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
