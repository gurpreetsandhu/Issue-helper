package com.stackroute.query.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Query {

     @Id
     private String id;
     private String q;
     private String a;
     private String link;

     public Query() {
          this.id = UUID.randomUUID().toString().replaceAll("-","");
     }

     public Query(String id, String q, String a, String link) {
          this.id = id;
          this.q = q;
          this.a = a;
          this.link = link;
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

     public String getLink() {
          return link;
     }

     public void setLink(String link) {
          this.link = link;
     }
}
