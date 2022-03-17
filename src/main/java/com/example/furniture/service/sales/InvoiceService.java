package com.example.furniture.service.sales;

import java.util.List;

import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface InvoiceService {

    public Page<BillDetails> getBillsClient(Optional<Integer> nit, Optional<Integer> page);
}
