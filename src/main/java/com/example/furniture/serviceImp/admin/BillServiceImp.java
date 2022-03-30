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
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImp implements BillService {

    @Autowired
    private BillDetailsRepository billDetailsRepository;
    @Autowired
    private BillRepository billRepository;

    @Override
    public Page<BillDetails> gerReportSalesXperiod(Optional<Date> date1, Optional<Date> date2, Optional<Integer> page){
            return this.billDetailsRepository.findAllByBill_DateTimeBetweenAndDateReturnIsNull(
                    date1.get(),
                    date2.get(),
                    PageRequest.of(page.orElse(0), 100)
            );
    }

    @Override
    public boolean doBill(Bill bill) {
        this.billRepository.save(bill);
        return this.billRepository.existsById(bill.getId());
    }

    @Override
    public Page<BillDetails> getReportEarningsXPeriod(Optional<Date> date1, Optional<Date> date2, Optional<Integer> page){
        return this.billDetailsRepository.findAllByBill_DateTimeBetween(
                date1.get(),
                date2.get(),
                PageRequest.of(page.orElse(0), 100)
        );
    }

    @Override
    public List<BillDetails> getReturnFurniture(Date dat1, Date date2) {
        return this.billDetailsRepository.findByCostLostGreaterThanAndDateReturnIsBetween(
                0,
                dat1,
                date2
        );
    }

    @Override
    public List<BillDetails> getReturnFurniture() {
        return this.billDetailsRepository.findByCostLostGreaterThan(0);
    }


}
