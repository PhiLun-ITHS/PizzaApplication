package com.example.PizzaApplication.repositories;

import com.example.PizzaApplication.entities.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Long> {
}
