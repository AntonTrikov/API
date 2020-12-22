package com.rest.API.controller.admin;

import com.rest.API.dto.model.IngredientDto;
import com.rest.API.dto.model.ProductDto;
import com.rest.API.model.IngredientModel;
import com.rest.API.model.ProductModel;
import com.rest.API.model.response.ApiResponseUtil;
import com.rest.API.service.admin.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.rest.API.service.admin.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto) {
        ProductModel productModel = productService.addProduct(productDto);
        return ResponseEntity.created(
                ApiResponseUtil.createLocation(productModel.getId(),
                        ServletUriComponentsBuilder.fromCurrentRequest())).build();
    }


}
