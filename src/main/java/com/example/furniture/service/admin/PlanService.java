package com.example.furniture.service.admin;

import com.example.furniture.model.Plan;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PlanService {

    public boolean createPlan(Plan plan);
    public Page<Plan> getAllPlans(Optional<Integer> pageNumber, Optional<String> name);
}
