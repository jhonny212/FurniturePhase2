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
    public List<BillDetails> gerReportSalesXperiod(Optional<Date> date1, Optional<Date> date2){
            return this.billDetailsRepository.findAllByBill_DateTimeBetweenAndDateReturnIsNull(
                    date1.get(),
                    date2.get()
            );
    }

    @Override
    public Object gerReportBestSellerXPeriod(Optional<Date> d1, Optional<Date> d2) throws ParseException {
        Object obj = this.billDetailsRepository.getBestSeller(
                d1.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("0001-01-01")),
                d2.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("2100-01-01"))
        );

        return obj;
    }

    @Override
    public Object getReportBestEarnerXPeriod(Optional<Date> d1, Optional<Date> d2) throws ParseException {
        Object obj = this.billDetailsRepository.getBestEarner(
                d1.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("0001-01-01")),
                d2.orElse(new SimpleDateFormat("yyyy-MM-dd").parse("2100-01-01"))
        );

        return obj;
    }

    @Override
    public boolean doBill(Bill bill) {
        this.billRepository.save(bill);
        return this.billRepository.existsById(bill.getId());
    }

    @Override
    public List<BillDetails> getReportEarningsXPeriod(Optional<Date> date1, Optional<Date> date2){
        return this.billDetailsRepository.findAllByBill_DateTimeBetween(
                date1.get(),
                date2.get()
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
