package com.rest.API.controller.admin;

import com.rest.API.dto.model.IngredientDto;
import com.rest.API.model.IngredientModel;
import com.rest.API.model.response.ApiResponseUtil;
import com.rest.API.security.service.JwtUserDetailsService;
import com.rest.API.service.admin.IngredientService;
import com.rest.API.uri.UriMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

@RestController
public class IngredientController {

    public static final Logger LOGGER= Logger.getLogger(IngredientController.class.getName());

    @Autowired
    IngredientService ingredientService;
    @CrossOrigin
    @PostMapping("/ingredient")
    public ResponseEntity<?> addIngredient(@Valid @RequestBody IngredientDto ingredientDto) {
        IngredientModel ingredientModel = ingredientService.addIngredient(ingredientDto);
        LOGGER.info("ingredientModel " + ingredientModel.toString());
        String uri = UriMappings.getIngredientUri(ingredientModel.getId());
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, uri).build();
    }
    @CrossOrigin
    @DeleteMapping("/ingredient/{ingredientId}")
    public ResponseEntity<?> removeIngredient(@PathVariable @NotNull int ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @CrossOrigin
    @PutMapping("/ingredient/{ingredientId}")
    public ResponseEntity<?> updateIngredient(@Valid @RequestBody IngredientDto ingredientDto,
                                              @PathVariable @NotNull String ingredientId) {
        ingredientDto.setId(ingredientId);
        ingredientService.putIngredient(ingredientDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @CrossOrigin
    @GetMapping("/ingredient/{ingredientId}")
    public ResponseEntity<?> getIngredientById(@PathVariable @NotNull int ingredientId) {
        IngredientDto dto = ingredientService.getIngredient(ingredientId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    @CrossOrigin
    @GetMapping("/ingredient")
    public ResponseEntity<?> getAllIngredients() {
        return ResponseEntity.status(HttpStatus.OK).body(ingredientService.getAllIngredients());
    }
}
