package com.rest.API.repository;

import com.rest.API.model.AppUserModel;
import com.rest.API.model.IngredientModel;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientModel, Integer> {
    public IngredientModel findByName(String name);
}