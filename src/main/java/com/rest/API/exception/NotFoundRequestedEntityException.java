package com.rest.API.exception;

import com.rest.API.model.ErrorHandledEntity;

public class NotFoundRequestedEntityException extends RuntimeException {
    public <T> NotFoundRequestedEntityException(String entityName,String entityId, int idValue){
        super(NotFoundRequestedEntityException.class.getSimpleName() +": "+
                entityName + " with  "
                + entityId + " " + idValue + " does not exists");
    }
}