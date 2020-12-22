package com.rest.API.repository;

import com.rest.API.model.IngredientModel;
import com.rest.API.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    public ProductModel findByName(String name);
}