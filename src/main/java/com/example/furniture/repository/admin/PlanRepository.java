package com.example.furniture.repository.admin;

import com.example.furniture.model.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,Integer> {

    Page<Plan> findByNameContainsIgnoreCase(String name, Pageable page);
}
