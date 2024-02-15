package com.stackroute.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.product.exception.ProductAlreadyExistsException;
import com.stackroute.product.exception.ProductNotFoundException;
import com.stackroute.product.model.Product;
import com.stackroute.product.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductControllerTest {

    private Product productOne;
    private Product productTwo;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.productController).build();
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

    @Test
    public void createSuccess() throws Exception {
        when(productService.addProduct(any())).thenReturn(this.productOne);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.productOne)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createFailure() throws Exception {
        when(productService.addProduct(any())).thenThrow(ProductAlreadyExistsException.class);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.productOne)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteSuccess() throws Exception {
        when(productService.deleteProduct(this.productOne.getId())).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/" + this.productOne.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteFailure() throws Exception {
        when(productService.deleteProduct(this.productOne.getId())).thenThrow(ProductNotFoundException.class);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/" + this.productOne.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}