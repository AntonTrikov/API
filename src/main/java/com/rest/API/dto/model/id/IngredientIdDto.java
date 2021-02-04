package com.rest.API.dto.model.id;

import com.rest.API.model.IngredientModel;
import com.rest.API.model.ProductTypologyModel;
import com.rest.API.uri.UriMappings;

public class IngredientIdDto extends IdDto{


    public IngredientIdDto(){super();}

    public IngredientIdDto(int id){
        super(String.valueOf(id),UriMappings.getIngredientUri(id));
    }

    @Override
    public String toString() {
        return "ProductTypologyIdDto{id=" + id  + '}';
    }
}
