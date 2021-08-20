package com.example.PizzaApplication.controllers;

import com.example.PizzaApplication.entities.Pizza;
import com.example.PizzaApplication.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaController(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/")
    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findPizzaById(@PathVariable("id") Long id){

        Pizza pizza = pizzaRepository.findPizzaById(id);

        if (pizza == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Pizza ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(pizza.toString());
    }

    @RequestMapping(value = "/={name}")
    public ResponseEntity<String> findPizzaByName(@PathVariable("name") String name) {
        Pizza pizza = pizzaRepository.findPizzaByName(name);

        if(pizza == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Pizza name: " + name);
        }
        return ResponseEntity.status(HttpStatus.OK).body(pizza.toString());
    }

    @PostMapping("/")
    public void addPizza(@RequestBody Pizza pizza){
        pizzaRepository.save(pizza);
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable("id") Long id){
        pizzaRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePizza(@RequestBody Pizza pizza, @PathVariable Long id) {

        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);

        if (!pizzaOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid ID: " + id);

        pizza.setId(id);

        pizzaRepository.save(pizza);

        return ResponseEntity.status(HttpStatus.OK).body("Pizza (" + id + ") successfully updated");
    }

}
