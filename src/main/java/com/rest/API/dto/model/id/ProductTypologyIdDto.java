package com.rest.API.dto.model.id;

import com.rest.API.dto.model.validation.IdValidation;
import com.rest.API.model.ProductTypologyModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import com.rest.API.uri.UriMappings;

public class ProductTypologyIdDto extends IdDto{

    public ProductTypologyIdDto(){super();}

    public ProductTypologyIdDto(int id){
        super(String.valueOf(id),UriMappings.getProductTypologyUri(id));
    }

    @Override
    public String toString() {
        return "ProductTypologyIdDto{id=" + id  + '}';
    }
}
