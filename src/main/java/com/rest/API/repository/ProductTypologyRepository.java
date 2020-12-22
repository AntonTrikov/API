package com.rest.API.repository;

import com.rest.API.model.IngredientModel;
import com.rest.API.model.ProductTypologyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypologyRepository extends JpaRepository<ProductTypologyModel, Integer> {
    ProductTypologyModel findByName(String name);
}
