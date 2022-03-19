package com.example.furniture.service.sales;

import java.util.Date;
import java.util.List;

import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface InvoiceService {

    public Page<BillDetails> getBillsClient(Optional<Integer> nit, Optional<Integer> page);

    public Page<BillDetails> getReturnClient(Optional<Integer> nit, Optional<Date> date1, Optional<Date> date2, Optional<Integer> page);

    public Page<BillDetails> getSalesToday(Optional<Date> date1, Optional<Integer> page);

    public Page<Bill> getBills(Optional<String> client, Optional<Date> date1, Optional<Date> date2, Optional<Integer> page);

    public Page<BillDetails> getBillsDetailsOfBill(Optional<Integer> idBill, Optional<Integer> page);
}
