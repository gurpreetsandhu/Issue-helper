package com.stackroute.account.model;

import java.util.UUID;

public class Transaction {

    private String id;
    private String userId;
    private String date;
    private String time;
    private int amount;
    private String type;
    private String to_from;

    public Transaction() {
        this.id = UUID.randomUUID().toString().replaceAll("-","");
    }

    public Transaction(String id, String userId, String date, String time, int amount, String type, String to_from) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.type = type;
        this.to_from = to_from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTo_from() {
        return to_from;
    }

    public void setTo_from(String to_from) {
        this.to_from = to_from;
    }
}
