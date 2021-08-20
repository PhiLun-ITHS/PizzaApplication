package com.example.PizzaApplication.repositories;

import com.example.PizzaApplication.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Pizza findPizzaByName(String name);
    Pizza findPizzaById(Long id);

}
