package com.example.PizzaApplication;

import com.example.PizzaApplication.entities.Pizza;
import com.example.PizzaApplication.repositories.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(PizzaRepository pizzaRepository){

        return (args) -> {
            if (pizzaRepository.count() == 0) {
                pizzaRepository.save(new Pizza(1L, "Margherita", "Tomatsås, Ost", 75));
                pizzaRepository.save(new Pizza(2L, "Vesuvio", "Tomatsås, Ost, Skinka", 80));
                pizzaRepository.save(new Pizza(3L, "Capricciosa", "Tomatsås, Ost, Skinka, Champinjon", 85));
            }
        };
    }
}
