package com.example.PizzaApplication.controllers;

import com.example.PizzaApplication.entities.Pizza;
import com.example.PizzaApplication.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaController(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/pizzas")
    public List<Pizza> pizzas(){

        return pizzaRepository.findAll();
    }

    @GetMapping("/pizzas/{id}")
    public ResponseEntity<Pizza> findPizzaById(@PathVariable("id") Long id){

        Pizza pizza = pizzaRepository.findPizzaById(id);

        if (pizza == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    @RequestMapping(value = "/={name}")
    public ResponseEntity<String> findPizzaByName(@PathVariable("name") String name) {
        Pizza pizza = pizzaRepository.findPizzaByName(name);

        if(pizza == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(pizza.toString());
    }

    @PostMapping("/")
    public void addPizza(@RequestBody Pizza pizza){
        pizzaRepository.save(pizza);
    }

    @DeleteMapping("/pizzas/{id}")
    public void deletePizza(@PathVariable("id") Long id){
        pizzaRepository.deleteById(id);
    }

}
