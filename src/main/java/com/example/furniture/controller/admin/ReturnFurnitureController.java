package com.example.furniture.controller.admin;

import com.example.furniture.model.BillDetails;
import com.example.furniture.repository.sales.BillDetailsRepository;
import com.example.furniture.serviceImp.admin.BillServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin/furniture")
public class ReturnFurnitureController {

    @Autowired
    private BillDetailsRepository billDetailsRepository;

    @GetMapping()
    public List<Object[]> getReturnFurniture(@RequestParam Optional<String> date1,
                                                @RequestParam Optional<String> date2){
        boolean valid = date1.isPresent() && date2.isPresent();
        if(valid){
            SimpleDateFormat format=new SimpleDateFormat("MM-dd-yyyy");
            try {
                List<Object[]> s =this.billDetailsRepository.getLost2(0,format.parse(date1.get()),format.parse(date2.get()));
                return s;
            } catch (Exception e) {
                return null;
            }
        }else{
            List<Object[]> s = this.billDetailsRepository.getLost1(0);
            return s;
        }
    }

}
