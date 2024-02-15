package com.stackroute.product.controller;

import com.stackroute.product.exception.ProductNotFoundException;
import com.stackroute.product.model.Product;
import com.stackroute.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@EnableFeignClients
@RibbonClient(name = "productcontroller")
public class ProductController {

    private ProductService productService;
    private ResponseEntity responseEntity;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity getProduct(){
        try{
            List<Product> productObjectList = this.productService.getAllProducts();
            this.responseEntity = new ResponseEntity(productObjectList, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return this.responseEntity;
    }

    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody Product product){
        try{
            Product returnStatus = this.productService.addProduct(product);
            if(returnStatus != null){
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.CREATED);
            }else{
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.CONFLICT);
            }
        }catch(Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

        return this.responseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") String id){
        try{
            boolean returnStatus = this.productService.deleteProduct(id);
            if(returnStatus) {
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.OK);
            }else{
                this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.NOT_FOUND);
            }
        } catch (ProductNotFoundException e) {
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            this.responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return this.responseEntity;
    }


    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAll(){
        boolean returnStatus = this.productService.deleteAll();
        this.responseEntity = new ResponseEntity(returnStatus, HttpStatus.OK);
        return this.responseEntity;
    }
}
