package com.rest.API.exception;

public class UnauthorizedException extends RuntimeException {
    public <T> UnauthorizedException( String roleAccess){
        super(UnauthorizedException.class.getSimpleName() +": "+
                "You need to have role " + roleAccess + " to access this endpoint");
    }
}

