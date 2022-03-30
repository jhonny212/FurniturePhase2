package com.example.furniture.repository.admin;

import com.example.furniture.model.AssignPlanPiece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignPlanPieceRepository extends JpaRepository<AssignPlanPiece,Integer> {
    List<AssignPlanPiece> findAllByPlan_Id(Integer id);
}
