package com.example.PizzaApplication.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class PizzaControllerSpringBootTest {


    @Autowired
    private PizzaController pizzaController;

    @Test
    void getAllPizzas(){
        assertThat(pizzaController.getAllPizzas()).hasSize(3);
    }
}