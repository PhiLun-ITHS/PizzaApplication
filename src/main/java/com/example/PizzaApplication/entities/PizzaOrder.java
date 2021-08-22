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
    @Basic
    private int totalPrice;

    public PizzaOrder(int orderId, String orderContent, int totalPrice) {
        this.orderId = orderId;
        this.orderContent = orderContent;
        this.totalPrice = totalPrice;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

}
