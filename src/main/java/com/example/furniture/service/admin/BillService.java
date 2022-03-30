package com.example.furniture.service.admin;

import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BillService {

    public Page<BillDetails> gerReportSalesXperiod(Optional<Date> date1, Optional<Date> date2, Optional<Integer> page);
    public boolean doBill(Bill bill);
    public List<BillDetails> getReturnFurniture(Date dat1,Date date2);
    public List<BillDetails> getReturnFurniture();
    public Page<BillDetails> getReportEarningsXPeriod(Optional<Date> date1, Optional<Date> date2, Optional<Integer> page);
}
