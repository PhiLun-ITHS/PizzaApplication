package com.example.PizzaApplication.controllers;

import com.example.PizzaApplication.entities.Pizza;
import com.example.PizzaApplication.entities.PizzaOrder;
import com.example.PizzaApplication.repositories.PizzaOrderRepository;
import com.example.PizzaApplication.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Random;


@RestController
public class OrderController {

    private final PizzaOrderRepository pizzaOrderRepository;
    private final PizzaRepository pizzaRepository;

    @Autowired
    public OrderController(PizzaOrderRepository pizzaOrderRepository, PizzaRepository pizzaRepository) {
        this.pizzaOrderRepository = pizzaOrderRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @PostMapping("/order={id}")
    public ResponseEntity<String> orderPizzas(@PathVariable("id") Long id){
        Pizza pizza = pizzaRepository.findPizzaById(id);

        if(pizza == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID: " + id);
        }

        String orderPizza = pizza.getName();

        Random random = new Random();
        int orderId = random.nextInt(1000);

        pizzaOrderRepository.save(new PizzaOrder(orderId, orderPizza));

        int price = pizzaRepository.findPizzaByName(orderPizza).getPrice();

        return ResponseEntity.status(HttpStatus.OK).body("Your pizza order (" + orderId + ") was successful\r\nPizza: " + orderPizza + "\r\nTotal price : " + price);
    }
}
