package com.example.furniture.controller.sales;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Furniture;
import com.example.furniture.model.FurnitureInBill;
import com.example.furniture.model.Profile;
import com.example.furniture.serviceImp.fabricate.FurnitureInBillServiceImp;
import com.example.furniture.util.ValidationService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
                furnitureInBillService.getFurnituresInBillBySession((Integer) claims.get("id_user")));
        return response;
    }

    @DeleteMapping("/on-session/{id}")
    public HashMap<String, Object> removeFurnitureFromBill(@PathVariable Integer id){
        HashMap<String, Object> response = new HashMap<>();
        response.put("wasDeleted",furnitureInBillService.removeFurnitureFromBill(id));
        return response;
    }

    @PostMapping("/on-session")
    public HashMap<String, Object> addFurnitureToBill(@RequestHeader("Authorization") String token, @RequestParam Integer code){
        JWTAuthorizationFilter jwtf = new JWTAuthorizationFilter();
        Claims claims = jwtf.getClaimsFromToken(token);

        Furniture furniture = new Furniture();
        furniture.setCode(code);
        Profile profile = new Profile();
        profile.setId((Integer) claims.get("id_user"));

        FurnitureInBill furnitureInBill = new FurnitureInBill(furniture,profile);
        HashMap<String, Object> response = new HashMap<>();
        response.put("wasAdded",false);
        if(this.validationService.validate(furnitureInBill)) response.replace("wasAdded",furnitureInBillService.addFurnitureToBill(furnitureInBill));
        return response;
    }
}
