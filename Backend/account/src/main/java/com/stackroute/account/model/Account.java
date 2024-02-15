package com.stackroute.account.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class Account {

     @Id
     private String id;
     private String account_number;
     private String ifsc_code;
     private String branch;
     private int balance;
     private String account_type;
     private String account_holder;
     private String loan_status;
     private List<Transaction> transacation_summary;

     public Account() {
          this.id = UUID.randomUUID().toString().replaceAll("-","");
          this.transacation_summary = new ArrayList<>();
     }

     public Account(String id, String account_number, String ifsc_code, String branch, int balance, String account_type, String account_holder, String loan_status, List<Transaction> transacation_summary) {
          this.id = id;
          this.account_number = account_number;
          this.ifsc_code = ifsc_code;
          this.branch = branch;
          this.balance = balance;
          this.account_type = account_type;
          this.account_holder = account_holder;
          this.loan_status = loan_status;
          this.transacation_summary = transacation_summary;
     }

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getAccount_number() {
          return account_number;
     }

     public void setAccount_number(String account_number) {
          this.account_number = account_number;
     }

     public String getIfsc_code() {
          return ifsc_code;
     }

     public void setIfsc_code(String ifsc_code) {
          this.ifsc_code = ifsc_code;
     }

     public String getBranch() {
          return branch;
     }

     public void setBranch(String branch) {
          this.branch = branch;
     }

     public int getBalance() {
          return balance;
     }

     public void setBalance(int balance) {
          this.balance = balance;
     }

     public String getAccount_type() {
          return account_type;
     }

     public void setAccount_type(String account_type) {
          this.account_type = account_type;
     }

     public String getAccount_holder() {
          return account_holder;
     }

     public void setAccount_holder(String account_holder) {
          this.account_holder = account_holder;
     }

     public String getLoan_status() {
          return loan_status;
     }

     public void setLoan_status(String loan_status) {
          this.loan_status = loan_status;
     }

     public List<Transaction> getTransacation_summary() {
          return transacation_summary;
     }

     public void setTransacation_summary(List<Transaction> transacation_summary) {
          this.transacation_summary = transacation_summary;
     }
}
