package com.stackroute.product.repository;

import com.stackroute.product.model.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    private Product productOne;
    private Product productTwo;
    @Before
    public void setUp(){

        this.productOne = new Product();
        this.productOne.setId("101");
        this.productOne.setImage_url("");
        this.productOne.setInterest_rate("8%");
        this.productOne.setLower_limit("2000");
        this.productOne.setName("productOne");
        this.productOne.setShopping_limit("10000");
        this.productOne.setTransaction_limit("5000");
        this.productOne.setYearly_charges("750");

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

    @After
    public void tearDown(){
        this.productRepository.deleteAll();
    }

    @Test
    public void saveSuccess(){
        this.productRepository.save(this.productOne);
        Product fetchProduct = this.productRepository.findById(this.productOne.getId()).get();
        Assert.assertEquals("101", fetchProduct.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteSuccess(){
        this.productRepository.save(this.productOne);
        this.productRepository.save(this.productTwo);
        Product fetchProduct = this.productRepository.findById(this.productOne.getId()).get();
        Assert.assertEquals("101", fetchProduct.getId());
        this.productRepository.deleteById(this.productOne.getId());
        Product fetchProductAgain = this.productRepository.findById(this.productOne.getId()).get();
    }

}