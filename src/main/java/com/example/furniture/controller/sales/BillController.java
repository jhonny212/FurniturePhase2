package com.example.furniture.controller.sales;

import com.example.furniture.config.JWTAuthorizationFilter;
import com.example.furniture.model.*;
import com.example.furniture.repository.fabricate.FurnitureRepository;
import com.example.furniture.repository.sales.BillDetailsRepository;
import com.example.furniture.repository.sales.BillRepository;
import com.example.furniture.repository.sales.ClientRepository;
import com.example.furniture.repository.sales.FurnitureInBillRepository;
import com.example.furniture.repository.user.UserRepository;
import com.example.furniture.serviceImp.admin.BillServiceImp;
import com.example.furniture.serviceImp.sales.ClientServiceImp;
import com.example.furniture.util.Utility;
import com.example.furniture.util.ValidationService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/sales/bill")
public class BillController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private BillServiceImp billServiceImp;
    @Autowired
    private BillDetailsRepository billDetailsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FurnitureRepository furnitureRepository;
    @Autowired
    private FurnitureInBillRepository furnitureInBillRepository;

    @PostMapping("")
    public HashMap<String, Object> doBill(@RequestHeader("Authorization") String token, @RequestBody Bill bill){
        Utility utilities = new Utility();
        JWTAuthorizationFilter jwt = new JWTAuthorizationFilter();
        HashMap<String, Object> response = new HashMap<>();

        Claims claims = jwt.getClaimsFromToken(token);
        response.put("wasAdded",false);

        Bill tmp = new Bill();
        tmp.setDateTime(utilities.getActualDate());
        Profile profile = this.userRepository.findByUsername((String)claims.get("username"));
        tmp.setProfile(profile);
        //tmp.setDetails(new ArrayList<>());
        if(!this.clientRepository.existsById(bill.getClient().getId())){
            this.clientRepository.save(bill.getClient());
        }
        tmp.setClient(bill.getClient());
        tmp.setTotal(bill.getTotal());

        if(validationService.validate(tmp)){
            response.replace("wasAdded",this.billServiceImp.doBill(tmp));
            for (BillDetails detail: bill.getDetails()) {
                detail.setBill(tmp);
                this.billDetailsRepository.save(detail);
                Furniture furniture = this.furnitureRepository.getById(detail.getFurniture().getCode());
                furniture.setStatus(2);
                this.furnitureRepository.save(furniture);
            }
            this.furnitureInBillRepository.deleteFurnitureInBillByProfile(profile);
        }
        return response;
    }
}
