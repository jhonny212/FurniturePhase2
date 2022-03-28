package com.example.furniture.serviceImp.admin;

import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import com.example.furniture.repository.sales.BillDetailsRepository;
import com.example.furniture.repository.sales.BillRepository;
import com.example.furniture.service.admin.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class BillServiceImp implements BillService {

    @Autowired
    private BillDetailsRepository billDetailsRepository;
    @Autowired
    private BillRepository billRepository;

    @Override
    public Page<BillDetails> gerReportSalesXperiod(Optional<Date> date1, Optional<Date> date2, Optional<Integer> page){
        try {
            return this.billDetailsRepository.findAllByBill_DateTimeBetweenAndAndDateReturnIsNull(
                    date1.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("0001-01-01")),
                    date2.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("2100-01-01")),
                    PageRequest.of(page.orElse(0), 100)
            );
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public boolean doBill(Bill bill) {
        this.billRepository.save(bill);
        return this.billRepository.existsById(bill.getId());
    }


}
