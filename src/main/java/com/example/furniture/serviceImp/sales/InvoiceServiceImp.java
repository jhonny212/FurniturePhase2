package com.example.furniture.serviceImp.sales;

import com.example.furniture.model.Bill;
import com.example.furniture.model.BillDetails;
import com.example.furniture.repository.sales.BillDetailsRepository;
import com.example.furniture.repository.sales.BillRepository;
import com.example.furniture.service.sales.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImp implements InvoiceService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillDetailsRepository billDetailsRepository;

    @Override
    public Page<BillDetails> getBillsClient(Optional<Integer> billId, Optional<Integer> page){
        return this.billDetailsRepository.findAllByBill_Id(billId.orElse(0), PageRequest.of(page.orElse(0),10));
    }

    public boolean isExisteBill(Integer billId){
        Optional<Bill> bill = this.billRepository.findById(billId);
        if (bill.isPresent())
            return true;
        return false;
    }
}
