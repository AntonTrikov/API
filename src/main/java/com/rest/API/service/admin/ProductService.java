package com.rest.API.service.admin;

import com.rest.API.dto.model.ProductDto;
import com.rest.API.exception.AlreadyExistsException;
import com.rest.API.exception.NotFoundRequestedEntityException;
import com.rest.API.model.ProductModel;
import com.rest.API.model.ProductTypologyModel;
import com.rest.API.repository.ProductRepository;
import com.rest.API.repository.ProductTypologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductTypologyRepository productTypologyRepository;

    public ProductModel addProduct(ProductDto productDto) {
        ProductModel productModel = productRepository.findByName(productDto.getName());
        if(productModel == null){
            productModel = new ProductModel();
            productModel.setName(productDto.getName());
            productModel.setPhoto(productDto.getPhoto()==null? null : productDto.getPhoto());
            productModel.setPrice(productDto.getPriceEur());
            int id = Integer.parseInt(productDto.getTypology().getId());
            Optional<ProductTypologyModel> productTypologyModelOptional =
                    productTypologyRepository.findById(id);
            if(productTypologyModelOptional.isPresent()){
                productModel.setProductTypology(productTypologyModelOptional.get());
            }else{
                throw new NotFoundRequestedEntityException(ProductTypologyModel.ENTITY_NAME,
                        "id",
                        productDto.getTypology().getId());
            }
            return productRepository.save(productModel);
        }
        throw new AlreadyExistsException(ProductModel.ENTITY_NAME,
                ProductModel.ID_NAME,
                productDto.getName());
    }

    public Object getAllProducts() {
        return productRepository.findAll().stream()
                .map(model -> new ProductDto(model))
                .collect(Collectors.toList());
    }
}
