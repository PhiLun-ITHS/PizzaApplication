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

    @GetMapping("/pizzas")
    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<String> findPizzaById(@PathVariable("id") Long id){

        Pizza pizza = pizzaRepository.findPizzaById(id);

        if (pizza == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Pizza ID: " + id);
        }

        return ResponseEntity.status(HttpStatus.OK).body(printPizza(pizza));
    }

    @PostMapping("/")
    public ResponseEntity<String> addPizza(@RequestBody Pizza pizza){

        Pizza checkPizza = pizzaRepository.findPizzaByName(pizza.getName());

        if(checkPizza == null){
            pizzaRepository.save(pizza);
            return ResponseEntity.status(HttpStatus.CREATED).body(printPizza(pizza));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pizza with that name already exist (" + pizza.getName() + ")");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePizza(@PathVariable("id") Long id){

        Pizza checkId = pizzaRepository.findPizzaById(id);

        if(checkId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID: " + id);
        }

        pizzaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pizza with id: (" + id + ") successfully deleted");
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

    @RequestMapping(value = "/name/search={name}")
    public ResponseEntity<String> findPizzaByName(@PathVariable("name") String name) {
        Pizza pizza = pizzaRepository.findPizzaByName(name);

        if(pizza == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Pizza name: " + name);
        }
        return ResponseEntity.status(HttpStatus.OK).body(printPizza(pizza));
    }

    @RequestMapping(value = "/ingredients/search={ingredients}")
    public ResponseEntity<String> findPizzaByIngredients(@PathVariable("ingredients") String ingredients) {

        Pizza pizza = pizzaRepository.findPizzaByIngredients(ingredients);

        if(pizza == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Ingredients name: " + ingredients);
        }
        return ResponseEntity.status(HttpStatus.OK).body(printPizza(pizza));

    }

    private String printPizza(Pizza pizza) {
        return "id: (" + pizza.getId() + ")\nname: (" + pizza.getName() + ")\ningredients: (" + pizza.getIngredients() + ")\nprice: (" + pizza.getPrice() + ")";
    }

}
