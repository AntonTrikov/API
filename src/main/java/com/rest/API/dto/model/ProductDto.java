package com.rest.API.dto.model;

import com.rest.API.dto.model.validation.IdValidation;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.springframework.validation.annotation.Validated;

public class ProductDto implements Serializable {
    @Min(   value = 1,
            message = "product id cannot be negative or blank",
            groups = IdValidation.class)
    int id;
    @NotBlank(message = "product name field cannot be blank")
    String name;

    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal price;

    String photo;

    @Min(value = 0, message = "product typologyId cannot be negative or blank")
    int typologyId;

    @NotNull(message = "product productIngredientList cannot be null")
    List <IngredientDto> productIngredientList;

    public ProductDto(){}

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getTypologyId() {
        return typologyId;
    }

    public void setTypologyId(int typologyId) {
        this.typologyId = typologyId;
    }

    public List<IngredientDto> getProductIngredientList() {
        return productIngredientList;
    }

    public void setProductIngredientList(@Validated(IdValidation.class) List<IngredientDto> productIngredientList) {
        this.productIngredientList = productIngredientList;
    }
}
