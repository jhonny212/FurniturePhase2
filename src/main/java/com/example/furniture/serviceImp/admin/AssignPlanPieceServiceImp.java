package com.example.furniture.serviceImp.admin;

import com.example.furniture.model.AssignPlanPiece;
import com.example.furniture.repository.admin.AssignPlanPieceRepository;
import com.example.furniture.service.admin.AssignPlanPieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignPlanPieceServiceImp implements AssignPlanPieceService {

    @Autowired
    private AssignPlanPieceRepository assignPlanPieceRepository;

    @Override
    public boolean createAssignPlanPiece(AssignPlanPiece assignment) {
        this.assignPlanPieceRepository.save(assignment);
        return this.assignPlanPieceRepository.existsById(assignment.getId());
    }
}
