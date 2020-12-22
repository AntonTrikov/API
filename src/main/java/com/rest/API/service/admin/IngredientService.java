package com.rest.API.service.admin;

import com.rest.API.dto.model.IngredientDto;
import com.rest.API.exception.AlreadyExistsException;
import com.rest.API.exception.CustomConstraintViolationException;
import com.rest.API.exception.NotFoundRequestedEntityException;
import com.rest.API.model.IngredientModel;
import com.rest.API.repository.IngredientRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public IngredientModel addIngredient(IngredientDto ingredientDto) {
        IngredientModel ingredientModel = ingredientRepository.findByName(ingredientDto.getName());
        if (ingredientModel == null) {
            ingredientModel = new IngredientModel();
            ingredientModel.setName(ingredientDto.getName());
            ingredientModel.setAllergenic(ingredientDto.isAllergenic());

            return ingredientRepository.save(ingredientModel);
        }
        throw new AlreadyExistsException(IngredientModel.getEntityName(),
                IngredientModel.getIdName(),
                ingredientDto.getName());

    }

    public void deleteIngredient(int ingredientId) {
        Optional<IngredientModel> ingredientModel = ingredientRepository.findById(ingredientId);
        if (ingredientModel.isPresent()) {
            ingredientRepository.delete(ingredientModel.get());
            return;
        }
        throw new NotFoundRequestedEntityException(IngredientModel.getEntityName(),
                IngredientModel.getIdName(),
                ingredientId);

    }

    public void putIngredient(IngredientDto ingredientDto) {
        int id = Integer.valueOf(ingredientDto.getId());
        Optional<IngredientModel> ingredientModel = ingredientRepository.findById(id);
        if (ingredientModel.isPresent()) {
            try {
                ingredientModel.get().setId(id);
                ingredientModel.get().setName(ingredientDto.getName());
                ingredientModel.get().setAllergenic(ingredientDto.isAllergenic());
                ingredientRepository.save(ingredientModel.get());
                return;
            } catch (Exception e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    throw new CustomConstraintViolationException(e.getMessage());
                }
            }
        }
        throw new NotFoundRequestedEntityException(IngredientModel.getEntityName(),
                IngredientModel.getIdName(),id);

    }

    public IngredientDto getIngredient(int ingredientId) {
        Optional<IngredientModel> ingredientModel = ingredientRepository.findById(ingredientId);
        if (ingredientModel.isPresent()) {
            return new IngredientDto(ingredientModel.get());
        }
        throw new NotFoundRequestedEntityException(IngredientModel.getEntityName(),
                IngredientModel.getIdName(),
                ingredientId);
    }

    public List<IngredientDto> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(model -> new IngredientDto(model))
                .collect(Collectors.toList());

    }
}
