package com.rest.API.dto.model;

import com.rest.API.dto.model.id.IngredientIdDto;
import com.rest.API.dto.model.id.ProductTypologyIdDto;
import com.rest.API.dto.model.validation.IdValidation;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.rest.API.uri.UriMappings;

import com.rest.API.model.ProductModel;
import org.springframework.validation.annotation.Validated;

public class ProductDto implements Serializable {
    @Min(   value = 1,
            message = "product id cannot be negative or blank",
            groups = IdValidation.class)
    int id;
    @NotBlank(message = "product name field cannot be blank")
    String name;

    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal priceEur;

    String photo;

    ProductTypologyIdDto typology;

    @NotNull(message = "product productIngredientList cannot be null")
    List <IngredientIdDto> ingredients;

    public ProductDto(ProductModel model){
        this.id  =model.getId();
        this.name = model.getName();
        this.priceEur =model.getPrice();
        this.photo = model.getPhoto();
        this.typology = new ProductTypologyIdDto(model.getProductTypology().getId());
        ingredients = new ArrayList<>();
        model.getIngredientSet().stream()
                .forEach(ingredientModel -> ingredients.add(new IngredientIdDto(ingredientModel.getId())));

    }

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

    public BigDecimal getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(BigDecimal priceEur) {
        this.priceEur = priceEur;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ProductTypologyIdDto getTypology() {
        return typology;
    }

    public void setTypology(ProductTypologyIdDto typology) {
        this.typology = typology;
    }

    public List<IngredientIdDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(@Validated(IdValidation.class) List<IngredientIdDto> ingredients) {
        this.ingredients = ingredients;
    }
}
