package com.rest.API.controller.admin;

import com.rest.API.dto.model.IngredientDto;
import com.rest.API.dto.model.ProductDto;
import com.rest.API.model.AppUserRoleEnum;
import com.rest.API.model.IngredientModel;
import com.rest.API.model.ProductModel;
import com.rest.API.model.response.ApiResponseUtil;
import com.rest.API.security.config.JwtRequestFilter;
import com.rest.API.service.admin.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.rest.API.service.admin.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts(@ApiIgnore @RequestHeader("Authorization") String authorization) throws Exception {
        jwtRequestFilter.validateAuthorizationUserRole(authorization,
                                    Arrays.asList(AppUserRoleEnum.RESTAURANT_OWNER,AppUserRoleEnum.ADMIN));
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto) {
        ProductModel productModel = productService.addProduct(productDto);
        return ResponseEntity.created(
                ApiResponseUtil.createLocation(productModel.getId(),
                        ServletUriComponentsBuilder.fromCurrentRequest())).build();
    }


}
