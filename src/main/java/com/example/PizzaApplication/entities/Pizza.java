package com.example.PizzaApplication.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String ingredients;
    private int price;

    public Pizza() {
    }

    public Pizza(String name, String ingredients, int price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public Pizza(Long id, String name, String ingredients, int price) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "(id: " + id +
                ") - (Pizza: " + name +
                ") - (Ingredients: " + ingredients +
                ") - (Price: " + price + ")";
    }
}
