package com.example.furniture.controller.sales;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.Furniture;
import com.example.furniture.model.FurnitureInBill;
import com.example.furniture.model.Profile;
import com.example.furniture.repository.user.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/on-session")
    public HashMap<String, Object> getFurnituresInBill(@RequestHeader("Authorization") String token){
        HashMap<String, Object> response = new HashMap<>();
        JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
        Claims claims = jwtaf.getClaimsFromToken(token);

        Profile tmpProfile = new Profile(); tmpProfile.setId((Integer) claims.get("id_user"));
        response.put("furnituresInBill",furnitureInBillService.getFurnituresInBillBySession(tmpProfile));
        return response;
    }

    @DeleteMapping("/on-session/{code}")
    public HashMap<String, Object> removeFurnitureFromBill(@PathVariable Integer code, @RequestHeader("Authorization") String token) {
        HashMap<String, Object> response = new HashMap<>();
        JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
        Claims claims = jwtaf.getClaimsFromToken(token);

        Profile tmpProfile = new Profile(); tmpProfile.setId((Integer) claims.get("id_user"));
        Furniture tmpFurniture = new Furniture(); tmpFurniture.setCode(code);
        response.put("wasDeleted",furnitureInBillService.removeFurnitureFromBill(tmpProfile,tmpFurniture));
        return response;
    }

    @DeleteMapping("/on-session")
    public HashMap<String, Object> removeAllFurnituresFromBill(@RequestHeader("Authorization") String token){
        HashMap<String, Object> response = new HashMap<>();
        JWTAuthorizationFilter jwtaf = new JWTAuthorizationFilter();
        Claims claims = jwtaf.getClaimsFromToken(token);
        
        Profile profile = this.userRepository.findByUsername((String)claims.get("username"));
        furnitureInBillService.deleteAllFurnituresInBillFromSession(profile);

        response.put("wasDeleted",true);
        return response;
    }

    @PostMapping("/on-session")
    public HashMap<String, Object> addFurnitureToBill(@RequestBody Furniture furniture, @RequestHeader("Authorization") String token){
        HashMap<String, Object> response = new HashMap<>();
        response.put("wasAdded",false);
        JWTAuthorizationFilter jwtf = new JWTAuthorizationFilter();
        Claims claims = jwtf.getClaimsFromToken(token);

        Profile profile = new Profile();
        profile.setId((Integer) claims.get("id_user"));
        FurnitureInBill furnitureInBill = new FurnitureInBill(furniture,profile);

        if(this.validationService.validate(furnitureInBill)) response.replace("wasAdded",furnitureInBillService.addFurnitureToBill(furnitureInBill));
        return response;
    }
}
