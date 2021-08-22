package com.example.PizzaApplication.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PizzaOrder {

    @Id
    private int orderId;
    @Basic
    private String orderContent;

    public PizzaOrder(int orderId, String orderContent) {
        this.orderId = orderId;
        this.orderContent = orderContent;
    }

    public PizzaOrder() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

}
