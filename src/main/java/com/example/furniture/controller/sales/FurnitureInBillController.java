package com.example.furniture.controller.sales;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.FurnitureInBill;
import com.example.furniture.service.sales.FurnitureInBillService;
import com.example.furniture.serviceImp.fabricate.FurnitureInBillServiceImp;
import com.example.furniture.util.ValidationService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/sales/furniture-in-bill")
public class FurnitureInBillController {

    @Autowired
    private FurnitureInBillServiceImp furnitureInBillService;
    @Autowired
    private ValidationService validationService;

    @GetMapping("/on-session")
    public HashMap<String, Object> getFurnituresInBill(@RequestHeader("Authorization") String token){
        HashMap<String, Object> response = new HashMap<>();
        JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
        Claims claims = jwtaf.getClaimsFromToken(token);
        response.put(
                "furnituresInBill",
                furnitureInBillService.getFurnituresInBillBySession(Integer.parseInt((String)claims.get("username"))));
        return response;
    }

    @DeleteMapping("/on-session/{id}")
    public HashMap<String, Object> removeFurnitureFromBill(@RequestParam Integer id){
        HashMap<String, Object> response = new HashMap<>();
        response.put("wasDeleted",furnitureInBillService.removeFurnitureFromBill(id));
        return response;
    }

    @PostMapping("/on-session")
    public HashMap<String, Object> addFurnitureToBill(@RequestBody FurnitureInBill furnitureInBill){
        HashMap<String, Object> response = new HashMap<>();
        response.put("wasAdded",false);
        if(this.validationService.validate(furnitureInBill)) response.replace("wasAdded",furnitureInBillService.addFurnitureToBill(furnitureInBill));
        return response;
    }
}
