package com.rest.API.service.admin;

import com.rest.API.dto.model.IngredientDto;
import com.rest.API.dto.model.ProductTypologyDto;
import com.rest.API.exception.AlreadyExistsException;
import com.rest.API.exception.CustomConstraintViolationException;
import com.rest.API.exception.NotFoundRequestedEntityException;
import com.rest.API.model.IngredientModel;
import com.rest.API.model.ProductTypologyModel;
import com.rest.API.repository.IngredientRepository;
import com.rest.API.repository.ProductTypologyRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductTypologyService {
    @Autowired
    ProductTypologyRepository productTypologyRepository;

    public ProductTypologyModel addProductTypology(ProductTypologyDto productTypologyDto) {
        ProductTypologyModel productTypologyModel =
                productTypologyRepository.findByName(productTypologyDto.getName());
        if (productTypologyModel == null) {
            return productTypologyRepository.save(
                                new ProductTypologyModel(productTypologyDto.getName()));
        }

        throw new AlreadyExistsException(ProductTypologyModel.ENTITY_NAME,
                ProductTypologyModel.ID_NAME,
                productTypologyDto.getName());
    }

    public void deleteProductTypology(int productTypologyId) {
        Optional<ProductTypologyModel> productTypologyModel = productTypologyRepository.findById(productTypologyId);
        if (productTypologyModel.isPresent()) {
            productTypologyRepository.delete(productTypologyModel.get());
            return;
        }
        throw newNotFoundRequestedEntityException(productTypologyId);
    }

    public void putProductTypology(ProductTypologyDto productTypologyDto) {
        Optional<ProductTypologyModel> productTypologyModel = productTypologyRepository.findById(productTypologyDto.getId());
        if (productTypologyModel.isPresent()) {
            try {
                productTypologyModel.get().setName(productTypologyDto.getName());
                productTypologyRepository.save(productTypologyModel.get());
                return;
            } catch (Exception e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    throw new CustomConstraintViolationException(e.getMessage());
                }
            }
        }
        throw newNotFoundRequestedEntityException(productTypologyDto.getId());
    }

    public ProductTypologyDto getProductTypology(int productTypologyId) {
        Optional<ProductTypologyModel> productTypologyModel = productTypologyRepository.findById(productTypologyId);
        if (productTypologyModel.isPresent()) {
            return new ProductTypologyDto(productTypologyModel.get());
        }
        throw newNotFoundRequestedEntityException(productTypologyId);

    }

    private NotFoundRequestedEntityException newNotFoundRequestedEntityException(int id){
        return new NotFoundRequestedEntityException(ProductTypologyModel.ENTITY_NAME,
                ProductTypologyModel.ID_NAME,
                id);
    }

    public List<ProductTypologyDto> getAllProductTypologies() {
        return productTypologyRepository.findAll().stream()
                .map(model -> new ProductTypologyDto(model))
                .collect(Collectors.toList());
    }
}
