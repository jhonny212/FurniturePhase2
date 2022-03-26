package com.example.furniture.serviceImp.admin;

import com.example.furniture.model.Plan;
import com.example.furniture.repository.admin.PlanRepository;
import com.example.furniture.service.admin.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImp implements PlanService {
    @Autowired
    private PlanRepository planRepository;

    @Override
    public boolean createPlan(Plan plan) {
        this.planRepository.save(plan);
        return this.planRepository.existsById(plan.getId());
    }
}
