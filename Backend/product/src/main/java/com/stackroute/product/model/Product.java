package com.stackroute.product.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Product {

     @Id
     private String id;
     private String name;
     private String interest_rate;
     private String lower_limit;
     private String yearly_charges;
     private String transaction_limit;
     private String shopping_limit;
     private String image_url;

     public Product() {
          this.id = UUID.randomUUID().toString().replaceAll("-","");
     }

     public Product(String id, String name, String interest_rate, String lower_limit, String yearly_charges, String transaction_limit, String shopping_limit, String image_url) {
          this.id = id;
          this.name = name;
          this.interest_rate = interest_rate;
          this.lower_limit = lower_limit;
          this.yearly_charges = yearly_charges;
          this.transaction_limit = transaction_limit;
          this.shopping_limit = shopping_limit;
          this.image_url = image_url;
     }

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getInterest_rate() {
          return interest_rate;
     }

     public void setInterest_rate(String interest_rate) {
          this.interest_rate = interest_rate;
     }

     public String getLower_limit() {
          return lower_limit;
     }

     public void setLower_limit(String lower_limit) {
          this.lower_limit = lower_limit;
     }

     public String getYearly_charges() {
          return yearly_charges;
     }

     public void setYearly_charges(String yearly_charges) {
          this.yearly_charges = yearly_charges;
     }

     public String getTransaction_limit() {
          return transaction_limit;
     }

     public void setTransaction_limit(String transaction_limit) {
          this.transaction_limit = transaction_limit;
     }

     public String getShopping_limit() {
          return shopping_limit;
     }

     public void setShopping_limit(String shopping_limit) {
          this.shopping_limit = shopping_limit;
     }

     public String getImage_url() {
          return image_url;
     }

     public void setImage_url(String image_url) {
          this.image_url = image_url;
     }
}
