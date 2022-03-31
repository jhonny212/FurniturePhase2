package com.example.furniture.service.admin;

import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BillService {

    public List<BillDetails> gerReportSalesXperiod(Optional<Date> date1, Optional<Date> date2);
    public boolean doBill(Bill bill);
    public List<BillDetails> getReturnFurniture(Date dat1,Date date2);
    public List<BillDetails> getReturnFurniture();
    Object gerReportBestSellerXPeriod(Optional<Date> d1, Optional<Date> d2) throws ParseException;
    Object getReportBestEarnerXPeriod(Optional<Date> d1, Optional<Date> d2) throws ParseException;
    public List<BillDetails> getReportEarningsXPeriod(Optional<Date> date1, Optional<Date> date2);
}
