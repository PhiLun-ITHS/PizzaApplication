package com.example.PizzaApplication.controllers;

import com.example.PizzaApplication.entities.Pizza;
import com.example.PizzaApplication.entities.PizzaOrder;
import com.example.PizzaApplication.repositories.PizzaOrderRepository;
import com.example.PizzaApplication.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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

    @PostMapping("/order")
    public ResponseEntity<String> orderPizzas(@RequestBody ArrayList<Pizza> orderList){

        List<Pizza> pizzaNames = new ArrayList<>();
        int totalPrice = 0;
        int totalPizzas = 0;

        for (Pizza p: orderList) {
            Pizza pizza = pizzaRepository.findPizzaByName(p.getName());
            if(pizza == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid pizza");
            }
            pizzaNames.add(pizzaRepository.findPizzaByName(p.getName()));

            totalPrice += pizzaRepository.findPizzaByName(p.getName()).getPrice();
            totalPizzas++;
        }
        int orderId = generateOrderId();
        pizzaOrderRepository.save(new PizzaOrder(orderId, pizzaString(pizzaNames), totalPrice));

        return ResponseEntity.status(HttpStatus.OK).body("Your pizza order (" + orderId + ") was successful" +
                "\r\nPizza (" + totalPizzas + "): " + pizzaString(pizzaNames) + "\r\nTotal price: " + totalPrice + ":-");
    }

    private int generateOrderId() {
        Random random = new Random();
        return random.nextInt(10000);
    }

    private String pizzaString(List<Pizza> pizzaNames) {
        return pizzaNames.toString().substring(1, pizzaNames.toString().length() - 1);
    }
}
