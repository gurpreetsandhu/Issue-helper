package com.stackroute.product.service;

import com.stackroute.product.exception.ProductAlreadyExistsException;
import com.stackroute.product.exception.ProductNotFoundException;
import com.stackroute.product.model.Product;
import com.stackroute.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductInterface {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        List<Product> productObjectList = this.productRepository.findAll();
        if(productObjectList == null || productObjectList.isEmpty()){
            throw new ProductNotFoundException("Product Not Found");
        }else{
            return productObjectList;
        }
    }

    @Override
    public Product addProduct(Product product) throws ProductAlreadyExistsException {
        return this.productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(String id) throws ProductNotFoundException {
        Optional<Product> productObjectOptional = this.productRepository.findById(id);
        if(!productObjectOptional.isPresent()){
            throw new ProductNotFoundException("Product Not Found");
        }
        this.productRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        this.productRepository.deleteAll();
        return true;
    }
}
