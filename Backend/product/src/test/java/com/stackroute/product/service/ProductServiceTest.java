package com.stackroute.product.service;

import com.stackroute.product.exception.ProductAlreadyExistsException;
import com.stackroute.product.exception.ProductNotFoundException;
import com.stackroute.product.model.Product;
import com.stackroute.product.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private Product productOne;
    private Product productTwo;
    private Optional<Product> productOptional;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.productOne = new Product();
        this.productOne.setId("101");
        this.productOne.setImage_url("");
        this.productOne.setInterest_rate("8%");
        this.productOne.setLower_limit("2000");
        this.productOne.setName("productOne");
        this.productOne.setShopping_limit("10000");
        this.productOne.setTransaction_limit("5000");
        this.productOne.setYearly_charges("750");

        this.productOptional = Optional.of(productOne);

        this.productTwo = new Product();
        this.productTwo.setId("202");
        this.productTwo.setImage_url("");
        this.productTwo.setInterest_rate("6%");
        this.productTwo.setLower_limit("7000");
        this.productTwo.setName("productTwo");
        this.productTwo.setShopping_limit("80000");
        this.productTwo.setTransaction_limit("9000");
        this.productTwo.setYearly_charges("550");
    }

    @Test
    public void createSuccess() throws ProductAlreadyExistsException {
        when(productRepository.save(this.productOne)).thenReturn(this.productOne);
        Product fetchedProduct = this.productService.addProduct(this.productOne);
        Assert.assertEquals(this.productOne, fetchedProduct);
    }

    @Test
    public void deleteSuccess() throws ProductNotFoundException {
        when(productRepository.findById(this.productOne.getId())).thenReturn(this.productOptional);
        boolean fetchedStatus = this.productService.deleteProduct(this.productOne.getId());
        Assert.assertEquals(true, fetchedStatus);
    }

    @Test(expected = ProductNotFoundException.class)
    public void deleteFailure() throws ProductNotFoundException {
        when(productRepository.findById(this.productOne.getId())).thenReturn(Optional.empty());
        boolean fetchedStatus = this.productService.deleteProduct(this.productOne.getId());
        Assert.assertEquals(false, fetchedStatus);
    }

}