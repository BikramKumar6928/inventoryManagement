package com.example.inventoryManagement.beans;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private OrderDetails orderDetails;
    private String message;


    public Issue(OrderDetails orderDetails, String message) {
        setOrderDetails(orderDetails);
        setMessage(message);
    }


    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public String getMessage() {
        return message;
    }
}
