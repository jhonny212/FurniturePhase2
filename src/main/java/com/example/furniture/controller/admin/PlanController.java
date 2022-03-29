package com.example.furniture.controller.admin;

import com.example.furniture.model.AssignPlanPiece;
import com.example.furniture.model.Plan;
import com.example.furniture.model.PlanData;
import com.example.furniture.serviceImp.admin.AssignPlanPieceServiceImp;
import com.example.furniture.serviceImp.admin.PlanServiceImp;
import com.example.furniture.serviceImp.fabricate.PieceServiceImp;
import com.example.furniture.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
@RequestMapping("/admin/plan")
public class PlanController {

    @Autowired
    private ValidationService validationService;
    @Autowired
    private PlanServiceImp planServiceImp;
    @Autowired
    private AssignPlanPieceServiceImp assignPlanPieceServiceImp;

    @PostMapping("")
    public HashMap<String, Object> doPlan(@RequestBody PlanData planData){
        HashMap<String, Object> response = new HashMap<>();
        response.put("wasAdded",false);

        if(validationService.validate(planData.getPlan())){
            response.replace("wasAdded",this.planServiceImp.createPlan(planData.getPlan()));
            for (AssignPlanPiece assignment: planData.getAssignments()) {
                assignment.setPlan(planData.getPlan());
                this.assignPlanPieceServiceImp.createAssignPlanPiece(assignment);
            }
        }
        return response;
    }
}
