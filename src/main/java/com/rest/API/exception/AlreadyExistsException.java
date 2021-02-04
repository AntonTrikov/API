package com.rest.API.exception;

import com.rest.API.model.ErrorHandledEntity;

public class AlreadyExistsException extends RuntimeException {
    public <T> AlreadyExistsException(String entityName, String entityId, int idValue){
        super(AlreadyExistsException.class.getSimpleName() +": "+
                entityName + " with "
                + entityId + " '" + idValue + "' already exists");
    }
    public <T> AlreadyExistsException(String entityName, String entityId, String idValue){
        super(AlreadyExistsException.class.getSimpleName() +": "+
                entityName + " with "
                + entityId + " '" + idValue + "' already exists");
    }
}
