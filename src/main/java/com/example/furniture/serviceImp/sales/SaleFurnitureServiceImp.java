package com.example.furniture.serviceImp.sales;

import com.example.furniture.model.BillDetails;
import com.example.furniture.model.Client;
import com.example.furniture.model.Furniture;
import com.example.furniture.repository.fabricate.FurnitureRepository;
import com.example.furniture.repository.sales.BillDetailsRepository;
import com.example.furniture.repository.sales.BillRepository;
import com.example.furniture.service.sales.SaleFurnitureService;
import com.example.furniture.serviceImp.fabricate.FurnitureServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaleFurnitureServiceImp implements SaleFurnitureService {
    @Autowired
    private BillDetailsRepository billDetailsRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private FurnitureRepository furnitureRepository;

    @Override
    public boolean repaymentFurniture(Furniture furniture, int id, Date date) {
        double cost = furniture.getCost();
        double lost = cost*1/3;
        lost = Math.round((lost)*100.0)/100.0;
        furniture.setCost(cost+lost);
        furniture.setPrice(furniture.getPrice()+lost);
        furniture.setStatus(0);
        furniture = furnitureRepository.save(furniture);
        if(furniture.getPlan()!=null){
            Optional<BillDetails> tmp = this.billDetailsRepository.findById(id);
            System.out.println("s");
            if(tmp.isPresent()){
                tmp.get().setCostLost(lost);
//                tmp.get().setBill(null);
                tmp.get().setDateReturn(date);
                this.billDetailsRepository.save(tmp.get());
                return true;
            }else{
                furniture.setCost(cost);
                furniture.setPrice(furniture.getPrice()-lost);
                furniture.setStatus(1);
                furnitureRepository.save(furniture);
            }
        }
        return false;
    }

    @Override
    public List<Object[]> getAllDetailsByBillId(int id){
        List<Object[]> t =billDetailsRepository.findAllDetailBils(id);
        return t;
    }

    @Override
    public Object getClientByIdBill(int id){
        Object t =  billDetailsRepository.findClient(id);
        return t;
    }


    public Object getEarningsTotal(Optional<Date> date1, Optional<Date> date2) throws ParseException {

        Object obj = this.billDetailsRepository.findEarnings(
                date1.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("0001-01-01")),
                date2.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("2100-01-01"))
        );

        return obj;
    }
}
