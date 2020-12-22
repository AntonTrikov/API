package com.rest.API.controller.admin;

import com.rest.API.dto.model.ProductTypologyDto;
import com.rest.API.dto.model.validation.IdValidation;
import com.rest.API.model.ProductTypologyModel;
import com.rest.API.model.response.ApiResponseUtil;
import com.rest.API.service.admin.ProductTypologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@RestController
@CrossOrigin
public class ProductTypologyController {
    @Autowired
    ProductTypologyService productTypologyService;

    @PostMapping("/admin/product-typology")
    public ResponseEntity<?> addProductTypology(@Valid @RequestBody ProductTypologyDto productTypologyDto) {
        ProductTypologyModel productTypologyModel = productTypologyService.addProductTypology(productTypologyDto);
        return ResponseEntity.created(
                ApiResponseUtil.createLocation(productTypologyModel.getId(),
                        ServletUriComponentsBuilder.fromCurrentRequest())).build();
    }

    @DeleteMapping("/admin/product-typology/{productTypologyId}")
    public ResponseEntity<?> deleteProductTypology(@Valid @RequestParam int productTypologyId) {
        productTypologyService.deleteProductTypology(productTypologyId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/admin/product-typology/{productTypologyId}")
    public ResponseEntity<?> updateProductTypology(@Valid @RequestBody ProductTypologyDto productTypologyDto,
                                                    @PathVariable @NotNull int productTypologyId) {
        productTypologyDto.setId(productTypologyId);
        productTypologyService.putProductTypology(productTypologyDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/admin/product-typology/{productTypologyId}")
    public ResponseEntity<?> getProductTypologyById(@PathVariable @NotNull int productTypologyId) {
        ProductTypologyDto dto = productTypologyService.getProductTypology(productTypologyId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/admin/product-typology")
    public ResponseEntity<?> getAllProductTypologies() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productTypologyService.getAllProductTypologies());
    }
}
