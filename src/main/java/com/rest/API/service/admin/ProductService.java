package com.rest.API.service.admin;

import com.rest.API.dto.model.ProductDto;
import com.rest.API.dto.model.ProductTypologyDto;
import com.rest.API.exception.AlreadyExistsException;
import com.rest.API.exception.NotFoundRequestedEntityException;
import com.rest.API.model.ProductModel;
import com.rest.API.model.ProductTypologyModel;
import com.rest.API.repository.ProductRepository;
import com.rest.API.repository.ProductTypologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            productModel.setPrice(productDto.getPrice());
            Optional<ProductTypologyModel> productTypologyModelOptional =
                    productTypologyRepository.findById(productDto.getTypologyId());
            if(productTypologyModelOptional.isPresent()){
                productModel.setProductTypology(productTypologyModelOptional.get());
            }else{
                throw new NotFoundRequestedEntityException(ProductTypologyModel.ENTITY_NAME,
                        "id",
                        productDto.getTypologyId());
            }
            return productRepository.save(productModel);
        }
        throw new AlreadyExistsException(ProductModel.ENTITY_NAME,
                ProductModel.ID_NAME,
                productDto.getName());
    }
}
