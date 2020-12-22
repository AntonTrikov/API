package com.rest.API.dto.model;

import com.rest.API.dto.model.validation.IdValidation;
import com.rest.API.model.ProductTypologyModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;


public class ProductTypologyDto {
    @Min(   value = 1,
            message = "product-typology id cannot be negative or blank",
            groups = IdValidation.class)
    int id;

    @NotBlank(message = "product-typology name field cannot be blank")
    String name;

    public ProductTypologyDto(){}

    public ProductTypologyDto(ProductTypologyModel model){
        this.id = model.getId();
        this.name = model.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductTypologyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
