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
//        return billDetailsRepository.findAll(PageRequest.of(page.orElse(0), 100));

//        try {
//            System.out.println(billDetailsRepository.findAll());
            System.out.println(date1.get());
            System.out.println(date2.get());
            System.out.println(page.get());
            return this.billDetailsRepository.findAllByBill_DateTimeBetween(
                    date1.get(),
                    date2.get(),
                    PageRequest.of(page.orElse(0), 100)
            );
//            return  billDetailsRepository.getBillDetailsReport(
//                    date1.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("0001-01-01")),
//                    date2.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("9999-01-01")),
//                    PageRequest.of(page.orElse(0), 100)
//            );
//        } catch (ParseException e) {
//            System.out.println(e);
//            return null;
//        }
    }

    @Override
    public boolean doBill(Bill bill) {
        this.billRepository.save(bill);
        return this.billRepository.existsById(bill.getId());
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
