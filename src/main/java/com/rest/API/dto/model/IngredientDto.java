package com.rest.API.dto.model;

import com.rest.API.dto.model.validation.IdValidation;
import com.rest.API.model.IngredientModel;
import com.rest.API.uri.UriMappings;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.*;
import java.io.Serializable;

@ApiModel
public class IngredientDto implements Serializable {
    @Min(value = 1,message = "product id cannot be negative or blank",groups = IdValidation.class)
    int id;

    @NotBlank(message = "ingredient name field cannot be blank")
    String name;

    @NotNull(message="ingredient isAllergenic field cannot be blank")
    Boolean allergenic;

    public IngredientDto(){}

    public IngredientDto(IngredientModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.allergenic = model.isAllergenic();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isAllergenic() {
        return allergenic;
    }

    public void setAllergenic(Boolean allergenic) {
        this.allergenic = allergenic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isAllergenic=" + allergenic +
                '}';
    }
}
