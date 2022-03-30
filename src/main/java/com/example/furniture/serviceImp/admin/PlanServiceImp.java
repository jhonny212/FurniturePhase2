package com.example.furniture.serviceImp.admin;

import com.example.furniture.model.Piece;
import com.example.furniture.model.Plan;
import com.example.furniture.repository.admin.PlanRepository;
import com.example.furniture.service.admin.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanServiceImp implements PlanService {
    @Autowired
    private PlanRepository planRepository;

    @Override
    public boolean createPlan(Plan plan) {
        this.planRepository.save(plan);
        return this.planRepository.existsById(plan.getId());
    }

    @Override
    public Page<Plan> getAllPlans(Optional<Integer> pageNumber, Optional<String> name){
        return this.planRepository.findByNameContainsIgnoreCase(
                name.orElse(""),
                PageRequest.of(
                        pageNumber.orElse(0),5
                )
        );
    }
}
