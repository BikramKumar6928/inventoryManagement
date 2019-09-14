package com.example.inventoryManagement.beans;

import javax.persistence.*;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    @OneToOne
    private OrderDetails orderDetails;
    private String message;


    public Issue(OrderDetails orderDetails, String message) {
        setOrderDetails(orderDetails);
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
