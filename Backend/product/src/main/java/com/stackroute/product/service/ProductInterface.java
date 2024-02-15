package com.stackroute.product.service;

import com.stackroute.product.exception.ProductAlreadyExistsException;
import com.stackroute.product.exception.ProductNotFoundException;
import com.stackroute.product.model.Product;

import java.util.List;

public interface ProductInterface {

    List<Product> getAllProducts() throws ProductNotFoundException;
    Product addProduct( Product product) throws ProductAlreadyExistsException;
    boolean deleteProduct( String id) throws ProductNotFoundException;
    boolean deleteAll();
}
