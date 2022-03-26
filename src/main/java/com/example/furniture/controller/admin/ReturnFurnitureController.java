package com.example.furniture.controller.admin;

import com.example.furniture.model.BillDetails;
import com.example.furniture.repository.sales.BillDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/admin/furniture")
public class ReturnFurnitureController {

    @Autowired
    private BillDetailsRepository billDetailsRepository;

    @GetMapping()
    public List<BillDetails> getReturnFurniture(@RequestParam Optional<String> date1,
                                                @RequestParam Optional<String> date2){
        boolean valid = date1.isPresent() && date2.isPresent();
        if(valid){
            SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
            try {
                return this.billDetailsRepository.findByCostLostGreaterThanAndDateReturnIsBetween(
                        0,
                        format.parse(date1.get()),
                        format.parse(date2.get())
                );
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return this.billDetailsRepository.findByCostLostGreaterThan(0);
        }
    }

}
