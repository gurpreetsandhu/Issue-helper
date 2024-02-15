package com.stackroute.faq.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Faq {

     @Id
     private String id;
     private String q;
     private String a;

     public Faq() {
          this.id = UUID.randomUUID().toString().replaceAll("-","");
     }

     public Faq(String id, String q, String a) {
          this.id = id;
          this.q = q;
          this.a = a;
     }

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getQ() {
          return q;
     }

     public void setQ(String q) {
          this.q = q;
     }

     public String getA() {
          return a;
     }

     public void setA(String a) {
          this.a = a;
     }
}
